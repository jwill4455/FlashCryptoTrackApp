package com.example.flashcryptotrackapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flashcryptotrackapp.domain.model.ExchangeRate
import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeRateDao {
    @Query("SELECT * FROM exchange_rates")
    fun getAllRates(): Flow<List<ExchangeRate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRates(rates: List<ExchangeRate>)
}