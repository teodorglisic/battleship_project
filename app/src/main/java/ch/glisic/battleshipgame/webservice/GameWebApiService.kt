package ch.glisic.battleshipgame.webservice

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GameWebApiService {

    @GET("ping")
    suspend fun getPing(): PingResult


    @POST("/game/join")
    suspend fun joinGame(@Body data: StartGameContainer): PostResult

    @POST("/game/fire")
    suspend fun fire(@Body data: FireBody): FireServerReply

    @POST("/game/enemyFire")
    suspend fun awaitEnemyFire(@Body data: EnemyFireBody): EnemyFireResponse

}