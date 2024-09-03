package com.example.shade.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shade.network.IndexApi
import com.example.shade.network.UvResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed interface IndexUiState {
    data class Success(val indexes: UvResponse) : IndexUiState
    data object Error : IndexUiState
    data object Loading : IndexUiState
}


class WeatherViewModel : ViewModel() {
    private val _indexUiState = MutableStateFlow<IndexUiState>(IndexUiState.Loading)
    val indexUiState = _indexUiState.asStateFlow()

    init {
        getUVIs()
    }

    private fun getUVIs() {
        viewModelScope.launch {
            try {
                val response = IndexApi.retrofitService.getPhotos(
                    latitude = 35.0,
                    longitude = 50.0
                )
                _indexUiState.value = IndexUiState.Success(response)
            } catch (e: Exception) {
                _indexUiState.value = IndexUiState.Error
            }
        }
    }
}