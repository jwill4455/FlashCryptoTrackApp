package com.example.flashcryptotrackapp.presentation.ui.exchangeratescreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcryptotrackapp.domain.usecase.GetCachedExchangeRatesUseCase
import com.example.flashcryptotrackapp.domain.usecase.GetCurrentExchangeRateUseCase
import com.example.flashcryptotrackapp.domain.usecase.GetHistoricalExchangeRatesUseCase
import com.example.flashcryptotrackapp.utils.Currency
import com.example.flashcryptotrackapp.utils.getCurrentDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val getHistoricalExchangeRatesUseCase: GetHistoricalExchangeRatesUseCase,
    private val getCurrentExchangeRateUseCase: GetCurrentExchangeRateUseCase,
    private val getCachedExchangeRatesUseCase: GetCachedExchangeRatesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ExchangeRateState())
    val state: StateFlow<ExchangeRateState> = _state

    private var selectedCurrency: Currency = Currency.EUR

    init {
        fetchCachedExchangeRates()
        fetchHistoricalExchangeRates()
        startUpdatingCurrentExchangeRate()
    }

    fun handleIntent(intent: ExchangeRateIntent) {
        when (intent) {
            is ExchangeRateIntent.LoadHistoricalRates -> fetchHistoricalExchangeRates()
            is ExchangeRateIntent.LoadCurrentRate -> startUpdatingCurrentExchangeRate()
            is ExchangeRateIntent.ChangeCurrency -> {
                selectedCurrency = intent.currency
                fetchHistoricalExchangeRates()
                startUpdatingCurrentExchangeRate()
            }
        }
    }

    private fun fetchCachedExchangeRates() {
        viewModelScope.launch {
            getCachedExchangeRatesUseCase.invoke().collect { rates ->
                _state.value = _state.value.copy(rates = rates)
            }
        }
    }

    private fun fetchHistoricalExchangeRates() {
        viewModelScope.launch {
            try {
                val rates = getHistoricalExchangeRatesUseCase(selectedCurrency.code)
                _state.value = _state.value.copy(rates = rates, lastUpdated = getCurrentDate().toString())
            } catch (e: Exception) {
                _state.value = ExchangeRateState(error = e.message)
            }
        }
    }

    private fun startUpdatingCurrentExchangeRate() {
        viewModelScope.launch {
            while (isActive) {
                try {
                    val currentRate = getCurrentExchangeRateUseCase(selectedCurrency.code)
                    _state.value = _state.value.copy(currentRate = currentRate, lastUpdated = getCurrentDate().toString())
                } catch (e: Exception) {
                    _state.value = _state.value.copy(error = e.message)
                }
                delay(60000)
            }
        }
    }

}


