package com.example.flashcryptotrackapp.data.repository

import com.example.flashcryptotrackapp.domain.model.ExchangeRate
import com.example.flashcryptotrackapp.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeExchangeRateRepository : ExchangeRateRepository {

    private val exchangeRates = mutableListOf(
        ExchangeRate("2022-01-01", 45000.0),
        ExchangeRate("2022-01-02", 46000.0)
    )

    override suspend fun getHistoricalExchangeRates(currency: String): List<ExchangeRate> {
        return exchangeRates
    }

    override fun getCachedRates(): Flow<List<ExchangeRate>> {
        return flowOf(exchangeRates)
    }

    override suspend fun getCurrentExchangeRate(currency: String): ExchangeRate {
        return exchangeRates.first()
    }
}