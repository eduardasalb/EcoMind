package com.example.ecomind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecomind.ui.theme.EcoMindTheme
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcoMindTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen() // Define a tela de Login como a inicial
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {

}

@Composable
fun EcoMind() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen() }
    }
}


