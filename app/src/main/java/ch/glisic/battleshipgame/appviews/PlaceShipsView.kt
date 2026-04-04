package ch.glisic.battleshipgame.appviews

import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.glisic.battleshipgame.R
import ch.glisic.battleshipgame.gameModel.Ships
import kotlin.math.sign

//Used Gemini for help with creating the Grid as Boxes, since I tried to use Buttons and it didn't work

@Composable
fun PlaceShipView(player: MediaPlayer) {

    var selectedShip: Ships? by remember {mutableStateOf(null)}
    val boatArray = remember { Array(10) { Array(10) { mutableStateOf(false) } } }
    var isHorizontal by remember { mutableStateOf(true) }
    val selectedShips = remember { mutableStateListOf<Ships>() }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)) {
        Text(
            text = stringResource(R.string.place_ship),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )

        // The Grid Board
        LazyVerticalGrid(
            columns = GridCells.Fixed(10), // Forces exactly 10 columns
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // Keeps the entire board perfectly square
                .border(2.dp, Color.Black), // An outer border for the board
            userScrollEnabled = false // Prevents the grid from scrolling
        ) {
            items(100) { index ->
                // Math to convert a 0-99 index back into row (i) and col (j)
                // From my understanding, you must use the items() with the LazyVerticalGrid, only that way it can work, I tried with a for loop, since I thought it was more efficient and the code was shorter, but that wasn't working.
                val row = index / 10
                val col = index % 10

                Box(
                    modifier = Modifier
                        .aspectRatio(1f) // Makes each individual cell a perfect square
                        .border(1.dp, Color.Gray) // Inner grid lines
                        .background(if (boatArray[row][col].value) Color.Green else Color.LightGray)
                        .clickable(enabled = selectedShip != null) {
                            selectedShip?.let { ship ->
                                if (isHorizontal) {

                                    if (col + ship.size <= 10) {
                                        // Help from gemini, using the none functionality
                                        val canPlace = (col until col + ship.size).none { index ->
                                            boatArray[row][index].value
                                        }

                                        if (canPlace) {
                                            for (i in col until (col + ship.size)) {
                                                boatArray[row][i].value = true
                                            }
                                            selectedShips.add(ship)
                                            selectedShip = null
                                            player.start()
                                        } else {
                                            // Help using the Toast message
                                            Toast.makeText(
                                                context,
                                                "Ships cannot overlap!", Toast.LENGTH_SHORT).show()
                                        }
                                    } else {
                                        // Ship goes out of bounds horizontally
                                        Toast.makeText(context, "Not enough space here!", Toast.LENGTH_SHORT).show()
                                    }


                                } else {

                                    if (row + ship.size <= 10) {
                                        val canPlace = (row until row + ship.size).none { index ->
                                            boatArray[index][col].value
                                        }

                                        if (canPlace) {
                                            for (i in row until (row + ship.size)) {
                                                boatArray[i][col].value = true
                                            }
                                            selectedShips.add(ship)
                                            selectedShip = null
                                            player.start()
                                        } else {
                                            // Target cells are occupied
                                            Toast.makeText(
                                                context,
                                                "Ships cannot overlap!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    } else {
                                        // Ship goes out of bounds vertically
                                        Toast.makeText(
                                            context,
                                            "Not enough space here!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }

                        },
                    contentAlignment = Alignment.Center
                ) {
                    // You can remove this Text later, it's just to show the coordinates work
                    Text(text = "$row,$col", fontSize = 10.sp, color = Color.Black)
                }
            }
        }

        if (selectedShip != null) {
            Text(text = "${selectedShip?.name} is selected, orientation: ${if (isHorizontal) "horizontal" else "vertical"}")
        }



        Button(
            onClick = { isHorizontal = !isHorizontal },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = if (isHorizontal) "Orientation: Horizontal" else "Orientation: Vertical")
        }

        Column(modifier = Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(50.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally) {


            Ships.entries.forEach { shipEntry ->

                if (!selectedShips.contains(shipEntry))
                BoatButton(shipEntry) {
                    selectedShip = shipEntry
                }

            }
        }


    }
}

@Composable
fun BoatButton(ship: Ships, onShipSelected: (Ships) -> Unit) {
    var rotation by remember { mutableFloatStateOf(0f) }

    // Proposal from Gemini, looks nicer this way
    val animatedAngle by animateFloatAsState(
        targetValue = rotation,
        label = "buttonRotation"
    )

    Box( modifier = Modifier
        .width(350.dp)
        .clickable(enabled = true) {
            Log.i("PlaceShipsView", "Button ${ship.name} pressed, rotated")
            onShipSelected(ship)
        }
    ) {
        Column() {
            Image(
                painter = painterResource(ship.icon),
                contentDescription = stringResource(R.string.carrier_content),
                contentScale = ContentScale.Fit
            )
            Text(text = ship.name)
        }

    }
}