package com.fcasarini.historyapp.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fcasarini.historyapp.viewmodel.MainViewModel

@Composable
fun HomepageScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val pageContent by viewModel.pageContent.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val query = viewModel.query

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        TextField(
            value = query,
            onValueChange = { viewModel.updateQuery(it) },
            label = { Text("Inserisci una domanda storica") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { viewModel.fetchPageContent(query) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Cerca")
        }
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Text("Caricamento in corso...")
        } else if (!pageContent.isNullOrEmpty()) {
            Text(
                text = pageContent!!,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp)
                    .verticalScroll(rememberScrollState())
            )
        } else {
            Text(
                "Nessun contenuto trovato.",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}


