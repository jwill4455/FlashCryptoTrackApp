package com.example.flashcryptotrackapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rates")
data class ExchangeRate(
    @PrimaryKey val date: String,
    val rate: Double,
)
