package com.example.flashcryptotrackapp.presentation.ui.exchangeratescreen

import com.example.flashcryptotrackapp.domain.model.ExchangeRate

data class ExchangeRateState(
    val rates: List<ExchangeRate> = emptyList(),
    val currentRate: ExchangeRate? = null,
    val error: String? = null,
    val lastUpdated: String? = null
)
