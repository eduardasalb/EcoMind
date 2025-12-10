package com.example.ecomind.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {

    private val _history = MutableStateFlow<List<String>>(emptyList())
    val history: StateFlow<List<String>> = _history

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            _history.value = listOf(
                "02/12 • Você economizou aproximadamente 10 litros de água.",
                "04/12 • Secou roupas ao sol, economizando energia.",
                "05/12 • Reduziu uso de plástico descartável.",
                "07/12 • Caminhou 3 km ao ar livre."
            )
        }
    }
}