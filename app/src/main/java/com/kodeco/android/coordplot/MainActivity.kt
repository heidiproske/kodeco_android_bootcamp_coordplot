package com.kodeco.android.coordplot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                SplashScreen()
            }
        }
    }
}

@Composable
fun SplashScreen() {
    // State to track whether the splash screen is completed
    var splashScreenCompleted by remember { mutableStateOf(false) }

    // Simulate some loading time
    LaunchedEffect(key1 = true) {
        delay(2000) // No user wants to wait long! This isn't ideal but doing it to show that we can see it.
        // Mark splash screen as completed after delay
        splashScreenCompleted = true
    }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Plotting...",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
        }
    }

    // Navigate to the plot after splash screen is completed
    if (splashScreenCompleted) {
        PlotSurface()
    }
}
