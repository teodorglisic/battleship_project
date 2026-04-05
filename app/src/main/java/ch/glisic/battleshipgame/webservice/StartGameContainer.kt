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


@Serializable
data class PingResult (
    val ping: Boolean
)


@Serializable
data class PostResult(
    val gameover: Boolean,
    val x: Int? = null,
    val y: Int? = null,
    val Error: String? = null
)

@Serializable
data class FireBody(
    val player: String,
    val gamekey: String,
    val x: Int,
    val y: Int
)


@Serializable
data class FireServerReply(
    val hit: Boolean,
    val shipsSunk: List<String>
)

@Serializable
data class EnemyFireBody(
    val player: String,
    val gamekey: String,
)

@Serializable
data class EnemyFireResponse(
    val x: Int,
    val y: Int,
    val gameOver: Boolean
)