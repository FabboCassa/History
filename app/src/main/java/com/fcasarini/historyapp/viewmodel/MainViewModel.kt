package com.fcasarini.historyapp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fcasarini.historyapp.repository.WikipediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: WikipediaRepository
) : ViewModel() {

    // Query dell'utente
    var query by mutableStateOf("")
        private set

    // Stato del contenuto della pagina
    private val _pageContent = MutableStateFlow<String?>(null)
    val pageContent: StateFlow<String?> = _pageContent

    // Stato per indicare il caricamento
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Aggiorna la query dell'utente
    fun updateQuery(newQuery: String) {
        query = newQuery
    }

    // Funzione per ottenere il contenuto di una pagina Wikipedia
    fun fetchPageContent(title: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                // Effettua la richiesta al repository
                val response = repository.getPageContent(title)

                // Estrai la mappa delle pagine dalla risposta
                val pages = response.query?.pages
                val page = pages?.values?.firstOrNull()

                // Aggiorna lo stato con il contenuto della pagina
                _pageContent.value = page?.extract ?: "Contenuto non disponibile"

                // Log del contenuto per debugging
                Log.d("WikipediaResponse", "Contenuto della pagina: ${_pageContent.value}")
            } catch (e: Exception) {
                // Gestisci l'errore impostando uno stato nullo
                _pageContent.value = "Errore durante il recupero della pagina."
                Log.e("WikipediaResponse", "Errore: ${e.message}", e)
            } finally {
                // Disabilita lo stato di caricamento
                _isLoading.value = false
            }
        }
    }
}


