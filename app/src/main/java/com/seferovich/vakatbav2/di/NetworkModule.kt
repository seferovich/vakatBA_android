package com.seferovich.vakatbav2.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.seferovich.vakatbav2.MyPreferences
import com.seferovich.vakatbav2.data.CitiesRepository
import com.seferovich.vakatbav2.data.NetworkCitesRepsoitory
import com.seferovich.vakatbav2.data.NetworkVakatRepository
import com.seferovich.vakatbav2.data.VakatRepository
import com.seferovich.vakatbav2.network.VakatApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl("https://api.vaktija.ba/vaktija/v1/")
            .build()
    }

    @Provides
    @Singleton
    fun provideVakatApi(retrofit: Retrofit): VakatApiService {
        return retrofit.create(VakatApiService::class.java)
    }

    @Provides
    fun provideVakatRepository(vakatApiService: VakatApiService): VakatRepository = NetworkVakatRepository(vakatApiService)

    @Provides
    fun provideCityRepository(vakatApiService: VakatApiService): CitiesRepository = NetworkCitesRepsoitory(vakatApiService)

    @Provides
    fun provideMyPreferences(@ApplicationContext context: Context): MyPreferences {
        return MyPreferences(context)
    }
}