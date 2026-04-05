package ch.glisic.battleshipgame.appviews

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.glisic.battleshipgame.R
import ch.glisic.battleshipgame.gameModel.StartGameModel
import ch.glisic.battleshipgame.webservice.ShipPosition

@Composable
fun GameBoardView(startGameModel: StartGameModel) {
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

        EnemyGameBoard()
        MyGameBoard(startGameModel)

    }
}

@Composable
fun MyGameBoard(startGameModel: StartGameModel) {
    val board = transformPosition(startGameModel.position)

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
                    .background(if (board[row][col]) Color.Green else Color.LightGray)
                    .clickable(enabled = true) {
                        println("$row,$col in MyGameBoard")
                    }

            ) {

                Text(text = "$row,$col", fontSize = 10.sp, color = Color.Black)
                println(startGameModel.position)
            }
        }
    }
}

@Composable
fun EnemyGameBoard() {
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
                    .background(Color.LightGray)
                    .clickable(enabled = true) {
                        println("$row,$col in EnemyGameBoard")
                    }

            ) {

                Text(text = "$row,$col", fontSize = 10.sp, color = Color.Black)
            }
        }
    }
}

fun transformPosition(position: SnapshotStateList<ShipPosition>): Array<Array<Boolean>> {
    var board = Array(10) {Array(10) {false} }

    for (ship in position) {

        val size = when (ship.ship) {
            "Carrier" -> 5
            "Battleship" -> 4
            "Destroyer" -> 3
            "Submarine" -> 3
            "PatrolBoat" -> 2
            else -> 0
        }

        if (ship.orientation == "horizontal") {
            for (col in ship.x until(ship.x + size)) {
                board[ship.y][col] = true
            }
        } else {
            for (row in ship.y until (ship.y + size)) {
                board[row][ship.x] = true
            }
        }
    }

    return board

}