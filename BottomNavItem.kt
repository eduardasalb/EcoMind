package com.example.ecomind.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("tips", "Dicas", Icons.Default.Support) // use ícone representativo
    object Challenges : BottomNavItem("challenges", "Desafios", Icons.Default.EmojiEvents)
    object Progress : BottomNavItem("progress", "Progresso", Icons.Default.ShowChart)
    object Profile : BottomNavItem("profile", "Perfil", Icons.Default.Person)
}
