package com.example.ecomind.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ecomind.model.ChallengeHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChallengeHistoryDao {
    @Insert
    suspend fun insert(history: ChallengeHistoryEntity)

    @Query("SELECT * FROM challenge_history ORDER BY completedAt DESC")
    fun getAllHistory(): Flow<List<ChallengeHistoryEntity>>

    @Query("SELECT COALESCE(SUM(pointsEarned), 0) FROM challenge_history")
    suspend fun getTotalPoints(): Int
}
