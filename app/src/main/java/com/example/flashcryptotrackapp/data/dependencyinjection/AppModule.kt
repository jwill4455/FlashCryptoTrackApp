package com.example.flashcryptotrackapp.data.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.example.flashcryptotrackapp.data.local.AppDatabase
import com.example.flashcryptotrackapp.data.local.ExchangeRateDao
import com.example.flashcryptotrackapp.data.remote.CoinGeckoApiService
import com.example.flashcryptotrackapp.data.repository.ExchangeRateRepositoryImpl
import com.example.flashcryptotrackapp.domain.repository.ExchangeRateRepository
import com.example.flashcryptotrackapp.utils.ConstantsUtil.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "exchange_rate_database"

        ).build()
    }

    @Provides
    @Singleton
    fun provideExchangeRateDao(database: AppDatabase): ExchangeRateDao {
        return database.exchangeRateDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CoinGeckoApiService {
        return retrofit.create(CoinGeckoApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideExchangeRateRepository(
        apiService: CoinGeckoApiService,
        exchangeRateDao: ExchangeRateDao
    ): ExchangeRateRepository {
        return ExchangeRateRepositoryImpl(apiService, exchangeRateDao)
    }
}