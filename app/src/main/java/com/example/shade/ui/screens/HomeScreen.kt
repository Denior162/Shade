package com.example.shade.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shade.R

@Composable
fun HomeScreen(
    indexUiState: IndexUiState,
    modifier: Modifier,
    retryAction: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when (indexUiState) {
            is IndexUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
            is IndexUiState.Success -> ResultScreen(
                uvResponse = indexUiState.indexes
                //(indexUiState as IndexUiState.Success).indexes
                ,
                modifier = modifier.fillMaxWidth()
            )

            is IndexUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
        }

    }
}

@Composable
fun ResultScreen(
    uvResponse: String
    //UvResponse
    , modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(text = uvResponse)
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(R.string.arms_spread), fontSize = 50.sp)
        Text(text = stringResource(R.string.error))
        Button(onClick = retryAction, Modifier.padding(4.dp)) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    CircularProgressIndicator()
}