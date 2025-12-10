package com.example.ecomind.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge_history")
data class ChallengeHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val challengeId: Long,
    val completedAt: Long = System.currentTimeMillis(),
    val pointsEarned: Int
)
