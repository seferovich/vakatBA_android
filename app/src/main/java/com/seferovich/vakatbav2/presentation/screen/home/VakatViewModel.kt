package com.seferovich.vakatbav2.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seferovich.vakatbav2.MyPreferences
import com.seferovich.vakatbav2.data.CitiesRepository
import com.seferovich.vakatbav2.data.VakatRepository
import com.seferovich.vakatbav2.model.Vakat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

sealed interface VakatUiState {
    data class Success(val vakat: Vakat): VakatUiState
    object Error: VakatUiState
    object Loading: VakatUiState
}
sealed interface CitiesUiState {
    data class Success(val cities: List<String>): CitiesUiState
    object Error: CitiesUiState
    object Loading: CitiesUiState
}
@HiltViewModel
class VakatViewModel @Inject constructor(
    private val vakatRepository: VakatRepository,
    private val citiesRepository: CitiesRepository,
    private val myPreferences: MyPreferences
): ViewModel() {
    var vakatUiState: VakatUiState by mutableStateOf(VakatUiState.Loading)
        private set
    var citiesUiState: CitiesUiState by mutableStateOf(CitiesUiState.Loading)
        private set



    init {
        getVakats(myPreferences.myValue)
        getCities()
    }


    fun setMyValue(value: Int){
        myPreferences.myValue = value
    }
    fun getVakats(city: Int) {
        viewModelScope.launch {
            vakatUiState = VakatUiState.Loading
            vakatUiState = try {
                VakatUiState.Success(vakatRepository.getVakats(city))
            } catch (e: IOException) {
                VakatUiState.Error
            } catch (e: HttpException) {
                VakatUiState.Error
            }
        }
    }

    fun getCities() {  // New function to fetch cities
        viewModelScope.launch {
            citiesUiState = CitiesUiState.Loading
            citiesUiState = try {
                CitiesUiState.Success(citiesRepository.getCities())
            } catch (e: IOException) {
                CitiesUiState.Error
            } catch (e: HttpException) {
                CitiesUiState.Error
            }
        }
    }
}