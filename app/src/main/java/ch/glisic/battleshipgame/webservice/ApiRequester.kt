package ch.glisic.battleshipgame.webservice

import ch.glisic.battleshipgame.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object ApiRequester {


    val client = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.MINUTES)
        .writeTimeout(5, TimeUnit.MINUTES)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(client)
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: GameWebApiService by lazy {
        retrofit.create(GameWebApiService::class.java)
    }
}