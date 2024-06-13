package com.example.flashcryptotrackapp.data.repository

import com.example.flashcryptotrackapp.data.local.ExchangeRateDao
import com.example.flashcryptotrackapp.data.remote.CoinGeckoApiService
import com.example.flashcryptotrackapp.domain.model.ExchangeRate
import com.example.flashcryptotrackapp.domain.repository.ExchangeRateRepository
import com.example.flashcryptotrackapp.utils.convertTimestampToDate
import com.example.flashcryptotrackapp.utils.getCurrentDate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeRateRepositoryImpl @Inject constructor(
    private val apiService: CoinGeckoApiService,
    private val exchangeRateDao: ExchangeRateDao
) : ExchangeRateRepository {

    override suspend fun getHistoricalExchangeRates(currency: String): List<ExchangeRate> {
        val response = apiService.getHistoricalExchangeRates(currency)
        val rates = response.prices.map { price ->
            ExchangeRate(date = convertTimestampToDate(price[0]), rate = price[1])
        }
        exchangeRateDao.insertAllRates(rates)
        return rates
    }

    override fun getCachedRates(): Flow<List<ExchangeRate>> {
        return exchangeRateDao.getAllRates()
    }

    override suspend fun getCurrentExchangeRate(currency: String): ExchangeRate {
        val response = apiService.getCurrentExchangeRate(id = "bitcoin", currency = currency)
        return ExchangeRate(date = getCurrentDate().toString(), rate = response.getPrice(currency))
    }
}