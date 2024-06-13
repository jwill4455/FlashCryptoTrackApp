package com.example.flashcryptotrackapp.presentation.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.flashcryptotrackapp.domain.repository.ExchangeRateRepository
import com.example.flashcryptotrackapp.domain.usecase.GetCachedExchangeRatesUseCase
import com.example.flashcryptotrackapp.domain.usecase.GetCurrentExchangeRateUseCase
import com.example.flashcryptotrackapp.domain.usecase.GetHistoricalExchangeRatesUseCase
import com.example.flashcryptotrackapp.presentation.ui.exchangeratescreen.ExchangeRateIntent
import com.example.flashcryptotrackapp.presentation.ui.exchangeratescreen.ExchangeRateViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class ExchangeRateViewModelTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: ExchangeRateRepository

    private lateinit var viewModel: ExchangeRateViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        viewModel = ExchangeRateViewModel(
            getHistoricalExchangeRatesUseCase = GetHistoricalExchangeRatesUseCase(repository),
            getCurrentExchangeRateUseCase = GetCurrentExchangeRateUseCase(repository),
            getCachedExchangeRatesUseCase = GetCachedExchangeRatesUseCase(repository)
        )
    }

    @Test
    fun testFetchHistoricalExchangeRates() = runTest {
        viewModel.handleIntent(ExchangeRateIntent.LoadHistoricalRates)
        val state = viewModel.state.value
        assertEquals(2, state.rates.size)
        assertEquals("2022-01-01", state.rates[0].date)
        assertEquals(45000.0, state.rates[0].rate, 0.0)
    }
}