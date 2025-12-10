package com.example.ecomind.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tips_history")
data class TipHistoryEntity(
    @PrimaryKey val id: String,
    val tipText: String,
    val timestamp: Long,
    val weatherSnapshot: String
)