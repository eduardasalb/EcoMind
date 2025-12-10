package com.example.ecomind.local

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecomind.model.ChallengeEntity
import com.example.ecomind.model.ChallengesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChallengesScreen(
    viewModel: ChallengesViewModel,
    onBack: () -> Unit
) {
    val challenges by viewModel.challenges.collectAsState(initial = emptyList())
    val totalPoints by viewModel.totalPoints.collectAsState(initial = 0)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Desafios 🌱") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(12.dp)
        ) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Seus pontos", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                        Text("$totalPoints pts", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
                    }

                    CircularProgressIndicator(
                        progress = ((totalPoints % 100) / 100f).coerceIn(0f, 1f),
                        strokeWidth = 6.dp,
                        modifier = Modifier.size(56.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                items(challenges) { challenge ->
                    ChallengeCard(challenge = challenge, onComplete = { viewModel.completeChallenge(it) })
                }
            }
        }
    }
}

@Composable
private fun ChallengeCard(challenge: ChallengeEntity, onComplete: (ChallengeEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(challenge.title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.height(4.dp))
                Text(challenge.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurface)
            }
            Button(
                onClick = { onComplete(challenge) },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Completar", color = MaterialTheme.colorScheme.onPrimary)
            }
        }
    }
}
