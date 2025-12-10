package com.example.ecomind.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ecomind.model.Tip
import com.example.ecomind.model.TipHistoryEntity
import com.example.ecomind.data.TipDao

@Database(
    entities = [
        Tip::class,
        TipHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tipDao(): TipDao
}
