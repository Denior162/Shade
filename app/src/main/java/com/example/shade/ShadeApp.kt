package com.example.shade

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shade.ui.WeatherViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShadeApp(modifier: Modifier) {
    val weatherViewModel: WeatherViewModel = viewModel()
    WeatherHomeScreen(
        modifier = Modifier
            .padding(64.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing),
        weatherUiState = weatherViewModel.weatherUiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })

}

class weatherData(
) {
    companion object {
        val iconRes: Int = (R.drawable.ic_launcher_background)
        val description: String = "pogoda super"
        val temperature: String = "35"
        val date: String = "Saturday"
        val city: String = "Kharkiv"

    }
}

@Composable
fun WeatherHomeScreen(weatherUiState: String, modifier: Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceDim,
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Surface {

            }
            Text(
                text = "${weatherData.city}, ${weatherData.date}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${weatherData.temperature}°C",
                    fontSize = 48.sp
                )
                Icon(
                    painter = painterResource(id = weatherData.iconRes),
                    contentDescription = "Weather icon"
                )
            }
            Text(
                text = weatherData.description,
                fontSize = 16.sp
            )
            Text(text = weatherUiState)
            // ... (остальные элементы интерфейса)
        }
    }
}
