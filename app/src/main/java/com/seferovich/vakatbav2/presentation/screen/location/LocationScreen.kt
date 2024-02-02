package com.seferovich.vakatbav2.presentation.screen.location

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.seferovich.vakatbav2.navigation.Screen
import com.seferovich.vakatbav2.presentation.screen.home.CitiesUiState
import com.seferovich.vakatbav2.presentation.screen.home.VakatViewModel
import com.seferovich.vakatbav2.ui.theme.Comfortaa

@Composable
fun LocationScreen(
    citiesUiState: CitiesUiState,
    viewModel: VakatViewModel,
    goBack: () -> Unit,
){

    when(citiesUiState){
        is CitiesUiState.Loading -> Text(text = "LOADING...")
        is CitiesUiState.Success -> {
            LazyColumn{
                itemsIndexed(citiesUiState.cities){ id: Int, city: String ->
//
                    CityRow(city = city, onClick = {
                        viewModel.getVakats(id)
                        viewModel.setMyValue(id)
                        goBack()
                    })
                    Divider()
                }
            }
        }
        is CitiesUiState.Error -> Text(text = "ERROR!")
    }


}


@Composable
fun CityRow(city: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable {
                onClick() }
            .fillMaxWidth()
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Compose your row here, for example:
        Text(
            text = city,
            fontSize = 18.sp,
            fontFamily = Comfortaa,
            fontWeight = FontWeight.Normal,
        )

    }
}