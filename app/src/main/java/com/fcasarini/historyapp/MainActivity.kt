package com.fcasarini.historyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fcasarini.historyapp.repository.WikipediaRepository
import com.fcasarini.historyapp.screens.HomepageScreen
import com.fcasarini.historyapp.ui.theme.HistoryAppTheme
import com.fcasarini.historyapp.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = MainViewModel(WikipediaRepository())
            HistoryAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomepageScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)  // Aggiungi il padding qui
                    )
                }
            }
        }
    }
}