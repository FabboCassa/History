package com.fcasarini.historyapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcasarini.historyapp.api.SearchResult
import com.fcasarini.historyapp.repository.WikipediaRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(
    private val repository: WikipediaRepository
) : ViewModel() {
    var query by mutableStateOf("")
        private set  // Limita l'accesso al setter

    private val _searchResults = MutableStateFlow<List<SearchResult>?>(null)
    val searchResults: StateFlow<List<SearchResult>?> = _searchResults

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Funzione rinominata per evitare conflitti con il setter implicito
    fun updateQuery(newQuery: String) {
        query = newQuery
    }

    // Funzione di ricerca
    fun search(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.searchArticles(query)
                _searchResults.value = response.query?.search

                // Log della risposta
                Log.d("WikipediaResponse", "Risultati della ricerca: ${_searchResults.value}")

            } catch (e: Exception) {
                _searchResults.value = null
                // Log degli errori
                Log.e("WikipediaResponse", "Errore durante la ricerca: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

}


