package com.example.shade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shade.ui.theme.ShadeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShadeTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { WeatherTopAppBar(scrollBehavior = scrollBehavior) }
                )
                { innerPadding ->
                    ShadeApp(modifier = Modifier.padding(innerPadding).safeDrawingPadding())
                }
            }
        }
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShadeTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                Scaffold(
                    modifier = Modifier.fillMaxSize().safeDrawingPadding(),
                    topBar = { WeatherTopAppBar(scrollBehavior = scrollBehavior) }
                ) { innerPadding ->
                    // Убедитесь, что вы используете правильные отступы
                    ShadeApp(modifier = Modifier.padding(innerPadding).safeDrawingPadding())
                    SomeFunction(modifier = Modifier.padding(innerPadding).safeDrawingPadding())
                }
            }
        }
    }
}

@Composable
fun SomeFunction(modifier: Modifier = Modifier) {
    Text(
        text = "1 \n 2\n 3\n 4\n 5",
        modifier = modifier // Применяем модификатор здесь
    )
}
*/