package com.example.ecomind.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeSreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bem-vindo(a)!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Meu Perfil",
                    modifier = Modifier.padding(start = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botão 2
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Configurações",
                    modifier = Modifier.padding(start = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}


