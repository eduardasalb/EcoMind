package com.example.ecomind.local

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipsScreen(
    onHistoryClick: () -> Unit,
    onChallengesClick: () -> Unit = {},
    onProgressClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    tipsOfDay: List<String> = defaultTips(),
    onTipDone: (tipText: String) -> Unit = {}
) {
    val doneSet = remember { mutableStateListOf<String>() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("EcoMind 🌿") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF2E7D32),
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = onHistoryClick) { Icon(Icons.Filled.History, contentDescription = "Histórico") }
                }
            )
        },
        bottomBar = {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8F5))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Dicas do dia 🌱", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(6.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 64.dp, max = 200.dp)
                    ) {
                        items(tipsOfDay) { tip ->
                            val done = tip in doneSet
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .clickable {
                                        if (!done) {
                                            doneSet.add(tip)
                                            onTipDone(tip)
                                        } else {
                                            doneSet.remove(tip)
                                        }
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(checked = done, onCheckedChange = {
                                    if (it) {
                                        doneSet.add(tip)
                                        onTipDone(tip)
                                    } else {
                                        doneSet.remove(tip)
                                    }
                                })
                                Spacer(Modifier.width(8.dp))
                                Text(tip, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Bem-vindo! 🌎\nEscolha uma ação e marque quando concluir.",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(20.dp))

            EcoButton("Desafios do Dia 🌱", Color(0xFF388E3C)) { onChallengesClick() }
            EcoButton("Seu Progresso 📊", Color(0xFF2E7D32)) { onProgressClick() }
            EcoButton("Seu Perfil 👤", Color(0xFF1B5E20)) { onProfileClick() }

            Spacer(Modifier.weight(1f))

            Text("Marque as dicas que você fez hoje para acompanhar seu progresso.", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
private fun defaultTips() = listOf(
    "Use copo reutilizável — evite descartáveis",
    "Desligue as luzes ao sair do cômodo",
    "Separe recicláveis hoje",
    "Tome um banho 2 minutos mais curto"
)

@Composable
fun EcoButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text, color = Color.White)
    }
}
