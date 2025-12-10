package com.example.ecomind.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecomind.repository.ChallengeRepository
import com.example.ecomind.usecase.CompleteChallengeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel que expõe:
 * - lista de desafios ativos
 * - histórico de conclusões (historyEntities)
 * - total de pontos
 * - ação para completar desafio
 */
class ChallengesViewModel(
    private val repo: ChallengeRepository,
    private val completeUseCase: CompleteChallengeUseCase
) : ViewModel() {

    val challenges: StateFlow<List<ChallengeEntity>> =
        repo.getActiveChallenges()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val historyEntities: StateFlow<List<ChallengeHistoryEntity>> =
        repo.getHistory()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val totalPoints: StateFlow<Int> = repo.getHistory()
        .map { list -> list.sumOf { it.pointsEarned } }
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    fun completeChallenge(challenge: ChallengeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            completeUseCase(challenge)
        }
    }
}
