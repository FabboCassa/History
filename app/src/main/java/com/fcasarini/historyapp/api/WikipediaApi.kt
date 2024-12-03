package com.fcasarini.historyapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Interfaccia Retrofit per le chiamate API
interface WikipediaApi {
    @GET("w/api.php")
    suspend fun getPageContent(
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("prop") prop: String = "extracts",
        @Query("titles") titles: String, // Specifica il titolo della pagina
        @Query("explaintext") explaintext: Boolean = true,
        @Query("exchars") exchars: Int = 5000 // Aumenta il limite del contenuto
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
