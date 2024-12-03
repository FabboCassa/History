package com.fcasarini.historyapp.repository

import com.fcasarini.historyapp.api.Query
import com.fcasarini.historyapp.api.SearchResult
import com.fcasarini.historyapp.api.WikipediaResponse

class FakeWikipediaRepository : WikipediaRepository() {
    override suspend fun searchArticles(query: String): WikipediaResponse {
        // Simuliamo una risposta mockata
        return WikipediaResponse(
            query = Query(
                search = listOf(
                    SearchResult(
                        title = "Genghis Khan",
                        snippet = "Genghis Khan (born Temüjin; c. 1162 – August 1227)..."
                    ),
                    SearchResult(
                        title = "Mongol Empire",
                        snippet = "The Mongol Empire was the largest contiguous empire in history."
                    )
                )
            )
        )
    }
}
