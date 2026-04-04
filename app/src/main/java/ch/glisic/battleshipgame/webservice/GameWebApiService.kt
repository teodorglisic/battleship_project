package ch.glisic.battleshipgame.webservice

import retrofit2.http.GET

interface GameWebApiService {

    @GET("ping")
    suspend fun getPing(): String
}