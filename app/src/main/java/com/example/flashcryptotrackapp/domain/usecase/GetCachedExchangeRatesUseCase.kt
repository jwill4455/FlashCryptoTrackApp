package com.example.flashcryptotrackapp.domain.usecase

import com.example.flashcryptotrackapp.domain.model.ExchangeRate
import com.example.flashcryptotrackapp.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCachedExchangeRatesUseCase @Inject constructor(
    private val repository: ExchangeRateRepository
) {
    fun invoke(): Flow<List<ExchangeRate>> {
        return repository.getCachedRates()
    }
}