package com.seferovich.vakatbav2.presentation.screen.home

import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.seferovich.vakatbav2.R
import com.seferovich.vakatbav2.presentation.components.VakatAndTime
import com.seferovich.vakatbav2.ui.theme.Comfortaa
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    vakatUiState: VakatUiState
) {
    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()

            ) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontFamily = Comfortaa,
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp
            )


            when (vakatUiState) {
                is VakatUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }
                is VakatUiState.Success -> {
                    Text(
                        text = vakatUiState.vakat.location,
                        fontFamily = Comfortaa,
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    Text(
                        text = "${vakatUiState.vakat.currDate.get(0)} / ${vakatUiState.vakat.currDate.get(1)}",
                        fontFamily = Comfortaa,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                    VakatAndTime(vakat = stringResource(id = R.string.namaz_1), time = vakatUiState.vakat.vakti.get(0))
                    VakatAndTime(vakat = stringResource(id = R.string.namaz_2), time = vakatUiState.vakat.vakti.get(1))
                    VakatAndTime(vakat = stringResource(id = R.string.namaz_3), time = vakatUiState.vakat.vakti.get(2))
                    VakatAndTime(vakat = stringResource(id = R.string.namaz_4), time = vakatUiState.vakat.vakti.get(3))
                    VakatAndTime(vakat = stringResource(id = R.string.namaz_5), time = vakatUiState.vakat.vakti.get(4))
                    VakatAndTime(vakat = stringResource(id = R.string.namaz_6), time = vakatUiState.vakat.vakti.get(5))
                }

                is VakatUiState.Error -> Text(text = "ERROR!")
            }

        }
    }
}
