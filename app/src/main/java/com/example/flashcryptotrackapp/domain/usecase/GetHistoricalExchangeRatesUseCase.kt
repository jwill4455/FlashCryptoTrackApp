package com.example.flashcryptotrackapp.domain.usecase

import com.example.flashcryptotrackapp.domain.model.ExchangeRate
import com.example.flashcryptotrackapp.domain.repository.ExchangeRateRepository
import javax.inject.Inject

class GetHistoricalExchangeRatesUseCase @Inject constructor(
    private val repository: ExchangeRateRepository
) {
    suspend operator fun invoke(currency: String): List<ExchangeRate> {
        return repository.getHistoricalExchangeRates(currency)
    }
}