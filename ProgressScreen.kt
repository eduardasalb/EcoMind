package com.example.ecomind.local

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecomind.model.ChallengeHistoryEntity
import com.example.ecomind.model.ChallengesViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressScreen(
    viewModel: ChallengesViewModel,
    onBack: () -> Unit
) {
    val totalPoints by viewModel.totalPoints.collectAsState(initial = 0)
    val history by viewModel.historyEntities.collectAsState(initial = emptyList<ChallengeHistoryEntity>())

    val dateFmt = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Progresso 🌿") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Seus pontos totais", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                Text("$totalPoints", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.height(16.dp))

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Histórico de conclusão", style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(8.dp))

                        if (history.isEmpty()) {
                            Text("Nenhuma conclusão registrada ainda.", style = MaterialTheme.typography.bodyMedium)
                        } else {
                            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                                items(history) { h: ChallengeHistoryEntity ->
                                    val dateStr = try {
                                        dateFmt.format(Date(h.completedAt))
                                    } catch (e: Exception) {
                                        h.completedAt.toString()
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 6.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text("- ${h.pointsEarned} pts", style = MaterialTheme.typography.bodyMedium)
                                        Text(dateStr, style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
