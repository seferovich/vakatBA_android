package com.seferovich.vakatbav2.network

import com.seferovich.vakatbav2.model.Vakat
import retrofit2.http.GET
import retrofit2.http.Path

interface VakatApiService {
    @GET("{city}")
    suspend fun getVakat(@Path("city") city: Int): Vakat

    @GET("lokacije")
    suspend fun getCities(): List<String>
}