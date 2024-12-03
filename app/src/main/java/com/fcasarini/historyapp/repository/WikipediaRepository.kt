package com.fcasarini.historyapp.repository

import android.util.Log
import com.fcasarini.historyapp.api.RetrofitInstance
import com.fcasarini.historyapp.api.WikipediaResponse

open class WikipediaRepository {
    open suspend fun getPageContent(title: String): WikipediaResponse {
        Log.d("API_CALL", "Fetching content for: $title")
        return RetrofitInstance.api.getPageContent(titles = title)
    }
}
