package com.example.ecomind.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenges")
data class ChallengeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String,
    val points: Int = 10,
    val isDaily: Boolean = true,
    val active: Boolean = true
)
