package com.example.shade

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shade.network.UvResponse
import com.example.shade.ui.screens.IndexUiState
import com.example.shade.ui.screens.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })

}

@Composable
fun WeatherHomeScreen(
    viewModel: WeatherViewModel = viewModel(),
    retryAction: () -> Unit,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val weatherUiState by viewModel.indexUiState.collectAsState()

        when (weatherUiState) {
            is IndexUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is IndexUiState.Success -> ResultScreen(
                uvIndex = (weatherUiState as IndexUiState.Success).indexes,
                modifier = modifier.fillMaxWidth()
            )

            is IndexUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
        }

    }
}

@Composable
fun ResultScreen(uvIndex: UvResponse, modifier: Modifier) {
    Text(text = uvIndex.now.time, modifier = Modifier.safeDrawingPadding())

}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier) {
    Column {
        Text(text = stringResource(R.string.error))
        Button(onClick = retryAction) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    CircularProgressIndicator()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShadeApp() {
    val weatherViewModel: WeatherViewModel = viewModel()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { WeatherTopAppBar(scrollBehavior = scrollBehavior) }
    )
    { innerPadding ->
        WeatherHomeScreen(
            modifier = Modifier
                .padding(innerPadding)
                .windowInsetsPadding(WindowInsets.safeDrawing), retryAction = {//TODO
            }
        )
    }
}