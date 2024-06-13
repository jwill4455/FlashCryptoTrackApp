package com.example.flashcryptotrackapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.flashcryptotrackapp.presentation.navigation.NavigationSetup
import com.example.flashcryptotrackapp.ui.theme.FlashCryptoTrackAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlashCryptoTrackAppTheme {
                NavigationSetup()
            }
        }
    }
}