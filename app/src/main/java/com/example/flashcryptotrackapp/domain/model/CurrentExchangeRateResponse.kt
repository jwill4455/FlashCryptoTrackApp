package com.example.flashcryptotrackapp.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentExchangeRateResponse(
    @SerialName("bitcoin")
    val bitcoin: Map<String, Double>?
) {
    fun getPrice(currency: String): Double {
        return bitcoin?.get(currency) ?: 0.0
    }
}
