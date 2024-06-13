package com.example.flashcryptotrackapp.data.dependencyinjection

import com.example.flashcryptotrackapp.data.repository.FakeExchangeRateRepository
import com.example.flashcryptotrackapp.domain.repository.ExchangeRateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    fun provideExchangeRateRepository(): ExchangeRateRepository {
        return FakeExchangeRateRepository()
    }
}