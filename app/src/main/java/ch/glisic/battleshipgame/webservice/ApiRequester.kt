package ch.glisic.battleshipgame.webservice

import ch.glisic.battleshipgame.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object ApiRequester {

    val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: GameWebApiService by lazy {
        retrofit.create(GameWebApiService::class.java)
    }
}