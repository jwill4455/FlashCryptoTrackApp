package com.example.flashcryptotrackapp.data.remote

import com.example.flashcryptotrackapp.domain.model.CurrentExchangeRateResponse
import com.example.flashcryptotrackapp.domain.model.HistoricalExchangeRate
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApiService {
    @GET("coins/bitcoin/market_chart")
    suspend fun getHistoricalExchangeRates(
        @Query("vs_currency") currency: String,
        @Query("days") days: Int = 14,
    ): HistoricalExchangeRate

    @GET("simple/price")
    suspend fun getCurrentExchangeRate(
        @Query("ids") id: String = "bitcoin",
        @Query("vs_currencies") currency: String,

    ): CurrentExchangeRateResponse
}