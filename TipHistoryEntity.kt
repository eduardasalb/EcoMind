package com.example.ecomind.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tip_history")
data class TipHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)
