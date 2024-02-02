package com.seferovich.vakatbav2.data

import com.seferovich.vakatbav2.model.Vakat
import com.seferovich.vakatbav2.network.VakatApiService

interface VakatRepository {
    suspend fun getVakats(city: Int): Vakat
}


class NetworkVakatRepository(
    private val vakatApiService: VakatApiService
): VakatRepository {
    override suspend fun getVakats(city: Int): Vakat = vakatApiService.getVakat(city)
}