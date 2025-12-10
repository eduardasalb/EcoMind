package com.example.ecomind.local

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onBackClick: () -> Unit,
    history: List<String> = sampleHistory()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Histórico") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
        ) {
            items(history) { item ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = item, style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Hoje • 12:00", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}

private fun sampleHistory() = listOf(
    "Dica: Use protetor solar — salva energia (12/12/2025)",
    "Dica: Separe recicláveis — garrafas PET (11/12/2025)"
)
