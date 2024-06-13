package com.example.flashcryptotrackapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class HistoricalExchangeRate(
    val prices: List<List<Double>>
)
