package ch.glisic.battleshipgame.appviews

import android.media.MediaPlayer
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.glisic.battleshipgame.R

//Used Gemini for help with creating the Grid as Boxes, since I tried to use Buttons and it didn't work

@Composable
fun PlaceShipView(player: MediaPlayer) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {
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

                var isPlaced by remember { mutableStateOf(false) }

                Box(
                    modifier = Modifier
                        .aspectRatio(1f) // Makes each individual cell a perfect square
                        .border(1.dp, Color.Gray) // Inner grid lines
                        .background(if (isPlaced) Color.DarkGray else Color.LightGray)
                        .clickable(enabled = !isPlaced) {
                            isPlaced = true
                            player.start()},
                    contentAlignment = Alignment.Center
                ) {
                    // You can remove this Text later, it's just to show the coordinates work
                    Text(text = "$row,$col", fontSize = 10.sp, color = Color.Black)
                }
            }
        }
        Row(modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically) {



            for (i in 1 ..40) {
                var rotation by remember { mutableFloatStateOf(0f) }

                // Proposal from Gemini, looks nicer this way
                val animatedAngle by animateFloatAsState(
                    targetValue = rotation,
                    label = "buttonRotation"
                )


                Button( modifier = Modifier.rotate(animatedAngle)
                    ,onClick = {
                        Log.i("PlaceShipsView", "Button $i pressed")
                        if (rotation == 0f) {
                            rotation = 90f
                        } else {
                            rotation = 0f
                        }
                    }) {
                    Text(text = "Button $i")
                }
            }
        }
    }
}