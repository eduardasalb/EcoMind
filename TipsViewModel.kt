package com.example.ecomind.ui.screens.tips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TipsViewModel : ViewModel() {

    private val _tips = MutableStateFlow<List<String>>(emptyList())
    val tips: StateFlow<List<String>> = _tips

    init {
        loadTips()
    }

    private fun loadTips() {
        viewModelScope.launch {
            _tips.value = listOf(
                "Aproveite dias ensolarados para secar roupas sem usar energia.",
                "Use ventilação natural antes de ligar ventiladores.",
                "Separe lixo reciclável sempre que possível.",
                "Economize água diminuindo o tempo de banho.",
                "Reutilize embalagens plásticas e de vidro."
            )
        }
    }
}
