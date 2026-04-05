package ch.glisic.battleshipgame.webservice

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GameWebApiService {

    @GET("ping")
    suspend fun getPing(): ResponseBody


    @POST("/game/join")
    suspend fun joinGame(@Body data: StartGameContainer): ResponseBody

    @POST("/game/join")
    suspend fun joinGameString(@Body data: String) : String
}