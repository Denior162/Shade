package com.example.shade.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shade.network.IndexApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface IndexUiState {
    data class Success(
        val indexes: //UvResponse
        String

    ) : IndexUiState

    //IndexUiState
    data object Error : IndexUiState
    data object Loading : IndexUiState
}


class IndexViewModel : ViewModel() {
    var indexUiState: IndexUiState by mutableStateOf(IndexUiState.Loading)
        private set


    init {
        getUVIs()
    }

    fun getUVIs() {
        viewModelScope.launch {
            indexUiState = IndexUiState.Loading
            indexUiState = try {
                val response = IndexApi.retrofitService.getIndexes(
                    latitude = 50.0,
                    longitude = 35.0
                )
                IndexUiState.Success(response)
            } catch (e: IOException) {
                IndexUiState.Error
            } catch (e: HttpException) {
                IndexUiState.Error
            } catch (e: Exception) {
                IndexUiState.Error
            }
        }
    }
}