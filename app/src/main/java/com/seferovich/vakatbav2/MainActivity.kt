package com.seferovich.vakatbav2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seferovich.vakatbav2.navigation.Screen
import com.seferovich.vakatbav2.presentation.screen.home.HomeScreen
import com.seferovich.vakatbav2.presentation.screen.home.VakatViewModel
import com.seferovich.vakatbav2.presentation.screen.location.LocationScreen
import com.seferovich.vakatbav2.ui.theme.VakatBAv2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: VakatViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VakatBAv2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val currentRoute = rememberSaveable { mutableStateOf(Screen.Home.route) }

                    Scaffold(
                        bottomBar = {
                            BottomAppBar(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                actions = {
                                    Spacer(Modifier.weight(1f))
                                    IconButton(onClick = {



                                        if (currentRoute.value == Screen.Home.route){
                                            currentRoute.value = Screen.Location.route
                                            navController.navigate(Screen.Location.route)
                                        } else if (currentRoute.value == Screen.Location.route){
                                            currentRoute.value = Screen.Home.route
                                            navController.popBackStack()
                                        }

                                    }) {
                                        if (currentRoute.value == Screen.Home.route) {
                                            Icon(Icons.Filled.LocationOn, contentDescription = "Location", Modifier.size(48.dp))
                                        } else {
                                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back", Modifier.size(48.dp))
                                        }
                                    }
                                    Spacer(Modifier.weight(1f))

                                },

                            )
                        },
                    ) { paddingValues ->
                        NavHost(navController = navController, startDestination = Screen.Home.route, modifier = Modifier.padding(paddingValues) ){
                            composable(Screen.Home.route){
                                HomeScreen(
                                    vakatUiState = viewModel.vakatUiState,
                                    navController = navController
                                )
                            }
                            composable(Screen.Location.route){
                                LocationScreen(
                                    citiesUiState = viewModel.citiesUiState,
                                    viewModel = viewModel,
                                    goBack = {
                                        navController.popBackStack()
                                        currentRoute.value = Screen.Home.route
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

