package com.example.flashcryptotrackapp.presentation.ui.exchangeratescreen

import com.example.flashcryptotrackapp.utils.Currency

sealed class ExchangeRateIntent {
    data object LoadHistoricalRates : ExchangeRateIntent()
    data object LoadCurrentRate : ExchangeRateIntent()
    data class ChangeCurrency(val currency: Currency) : ExchangeRateIntent()
}