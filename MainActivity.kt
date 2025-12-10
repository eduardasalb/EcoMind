package com.example.ecomind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ecomind.local.AppNavGraph
import com.example.ecomind.ui.theme.EcoMindTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoMindTheme {
                AppNavGraph()
            }
        }
    }
}
