package com.example.ecomind.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ecomind.data.TipDao
import com.example.ecomind.model.TipHistoryEntity

@Database(
    entities = [TipHistoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TipDatabase : RoomDatabase() {
    abstract fun tipDao(): TipDao
}
