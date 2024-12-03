package com.fcasarini.historyapp.api

data class WikipediaResponse(
    val query: Query?
)

data class Query(
    val pages: Map<String, Page> // Mappa con ID pagina come chiave
)

data class Page(
    val pageid: Int,
    val title: String,
    val extract: String // Contenuto dettagliato della pagina
)
