package com.seferovich.vakatbav2.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vakat(
    @SerialName("id") val cityId: Int,
    @SerialName("lokacija") val location: String,
    @SerialName("datum") val currDate: List<String>,
    @SerialName("vakat") val vakti: List<String>
)
