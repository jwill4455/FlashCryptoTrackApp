package com.example.flashcryptotrackapp.domain.repository

import com.example.flashcryptotrackapp.domain.model.ExchangeRate
import kotlinx.coroutines.flow.Flow

interface ExchangeRateRepository {
    suspend fun getHistoricalExchangeRates(currency: String): List<ExchangeRate>
    fun getCachedRates(): Flow<List<ExchangeRate>>
    suspend fun getCurrentExchangeRate(currency: String): ExchangeRate
}