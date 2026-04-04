package ch.glisic.battleshipgame.gameModel

import ch.glisic.battleshipgame.R

enum class Ships(val size: Int, val icon: Int) {
    Carrier(5, R.drawable.carrier_grid),
    Battleship(4, R.drawable.battleship_grid),
    Destroyer(3, R.drawable.destroyer_grid),
    Submarine(3, R.drawable.submarine_grid),
    PatrolBoat(2, R.drawable.patrol_boat_grid)
}