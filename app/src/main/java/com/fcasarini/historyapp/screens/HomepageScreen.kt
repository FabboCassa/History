package com.fcasarini.historyapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.query
import com.fcasarini.historyapp.repository.FakeWikipediaRepository
import com.fcasarini.historyapp.ui.theme.HistoryAppTheme
import com.fcasarini.historyapp.viewmodel.MainViewModel

@Composable
fun HomepageScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val searchResults by viewModel.searchResults.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val query = viewModel.query

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = query,
            onValueChange = { viewModel.updateQuery(it) },  // Cambiato setQuery in updateQuery
            label = { Text("Inserisci una domanda storica") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { viewModel.search(query) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cerca")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Text("Caricamento in corso...")
        } else if (!searchResults.isNullOrEmpty()) {
            LazyColumn {
                items(searchResults!!) { result ->
                    Text(
                        text = result.title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = android.text.Html.fromHtml(result.snippet, android.text.Html.FROM_HTML_MODE_LEGACY).toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        } else {
            Text("Nessun risultato trovato.", modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}





@Preview(showBackground = true)
@Composable
fun HomepagePreview() {
    val fakeRepository = FakeWikipediaRepository()
    val viewModel = MainViewModel(fakeRepository)

    HistoryAppTheme {
        HomepageScreen(viewModel = viewModel)
    }
}

