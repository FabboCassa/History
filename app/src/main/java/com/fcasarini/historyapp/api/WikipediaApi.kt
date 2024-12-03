package com.fcasarini.historyapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Interfaccia Retrofit per le chiamate API
interface WikipediaApi {
    @GET("w/api.php")
    suspend fun searchArticles(
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("list") list: String = "search",
        @Query("srsearch") query: String
    ): WikipediaResponse
}

// Oggetto per gestire Retrofit
object RetrofitInstance {
    private const val BASE_URL = "https://en.wikipedia.org/"

    val api: WikipediaApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WikipediaApi::class.java)
    }
}
