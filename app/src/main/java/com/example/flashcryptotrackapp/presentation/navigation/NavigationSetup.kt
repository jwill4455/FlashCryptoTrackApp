package com.example.flashcryptotrackapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashcryptotrackapp.presentation.ui.exchangeratescreen.ExchangeRateScreen

@Composable
fun NavigationSetup() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = ExchangeRateScreen) {
        composable<ExchangeRateScreen> {
            ExchangeRateScreen()
        }
    }
}