package com.seferovich.vakatbav2.data

import com.seferovich.vakatbav2.network.VakatApiService

interface CitiesRepository {
    suspend fun getCities(): List<String>
}

class NetworkCitesRepsoitory(
    private val vakatApiService: VakatApiService
): CitiesRepository{
    override suspend fun getCities(): List<String> = vakatApiService.getCities()
}