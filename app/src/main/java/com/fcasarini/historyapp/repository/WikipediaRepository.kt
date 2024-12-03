package com.fcasarini.historyapp.repository

import android.util.Log
import com.fcasarini.historyapp.api.RetrofitInstance
import com.fcasarini.historyapp.api.WikipediaResponse

open class WikipediaRepository {
    open suspend fun searchArticles(query: String): WikipediaResponse {
        Log.d("API_CALL", "Searching articles for: $query")
        return RetrofitInstance.api.searchArticles(query = query)
    }

}
