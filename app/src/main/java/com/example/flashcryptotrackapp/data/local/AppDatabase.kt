package com.example.flashcryptotrackapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flashcryptotrackapp.domain.model.ExchangeRate

@Database(entities = [ExchangeRate::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
}