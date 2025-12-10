package com.example.ecomind.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ecomind.model.TipHistoryEntity

@Dao
interface TipDao {
    @Insert
    suspend fun insertTip(tip: TipHistoryEntity): Long

    @Query("SELECT * FROM tip_history ORDER BY timestamp DESC")
    suspend fun getAllHistory(): List<TipHistoryEntity>
}
