package ch.glisic.battleshipgame.webservice

import kotlinx.serialization.Serializable

@Serializable
data class StartGameContainer (
    val gamekey: String,
    val player: String,
    val ships: List<ShipPosition>
)

@Serializable
data class ShipPosition (
    val orientation: String,
    val x: Int,
    val y: Int,
    val ship: String
)