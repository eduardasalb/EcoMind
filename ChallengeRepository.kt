package com.example.ecomind.repository

import com.example.ecomind.model.ChallengeEntity
import com.example.ecomind.model.ChallengeHistoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Repositório em MEMÓRIA para desafios.
 * Não usa Room e NÃO precisa de ChallengeDao ou ChallengeHistoryDao.
 */

class ChallengeRepository {

    private val challenges = MutableStateFlow<List<ChallengeEntity>>(emptyList())
    private val history = MutableStateFlow<List<ChallengeHistoryEntity>>(emptyList())

    init {
        challenges.value = listOf(
            ChallengeEntity(
                id = 1,
                title = "Economize água no banho",
                description = "Reduza 2 minutos do tempo de banho",
                points = 10
            ),
            ChallengeEntity(
                id = 2,
                title = "Separar reciclagem",
                description = "Separe plástico, papel e vidro",
                points = 15
            )
        )
    }

    fun getActiveChallenges(): Flow<List<ChallengeEntity>> = challenges.asStateFlow()

    suspend fun insertChallenge(challenge: ChallengeEntity) {
        challenges.value = challenges.value + challenge
    }

    suspend fun completeChallenge(historyEntry: ChallengeHistoryEntity) {
        history.value = history.value + historyEntry
    }

    fun getHistory(): Flow<List<ChallengeHistoryEntity>> = history.asStateFlow()

    suspend fun getTotalPoints(): Int =
        history.value.sumOf { it.pointsEarned }
}
