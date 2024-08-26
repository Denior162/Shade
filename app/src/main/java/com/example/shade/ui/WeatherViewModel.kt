package com.example.shade.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shade.network.WeatherApi
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    var weatherUiState: String by mutableStateOf("")
        private set

    init {
        getWeather()
    }

     private fun getWeather( ) {
         viewModelScope.launch {
             val receivedWeatherData = WeatherApi.retrofitService.getWeather(cityName = "Kharkiv")
             weatherUiState = receivedWeatherData
         }
     }
}