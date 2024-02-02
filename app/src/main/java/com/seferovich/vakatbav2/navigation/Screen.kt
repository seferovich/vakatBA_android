package com.seferovich.vakatbav2.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Location : Screen("location")
}

