package com.example.flashcryptotrackapp.domain.usecase

import com.example.flashcryptotrackapp.domain.model.ExchangeRate
import com.example.flashcryptotrackapp.domain.repository.ExchangeRateRepository
import javax.inject.Inject

class GetCurrentExchangeRateUseCase @Inject constructor(
    private val repository: ExchangeRateRepository
) {
    suspend operator fun invoke(currency: String): ExchangeRate {
        return repository.getCurrentExchangeRate(currency)
    }
}
