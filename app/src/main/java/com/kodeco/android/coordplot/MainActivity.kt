package com.kodeco.android.coordplot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                PlotSurface()
            }
        }
    }
}

// Builds out the plot surface
@Composable
fun PlotSurface() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        var axisXValue: Float by rememberSaveable { mutableFloatStateOf(0.5f) }
        var axisYValue: Float by rememberSaveable { mutableFloatStateOf(0.5f) }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Map(xPercent = 0.5f, yPercent = 0.5f)
            Slider(value = axisXValue, onValueChange = { axisXValue = it })
            Slider(value = axisYValue, onValueChange = { axisYValue = it })
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PlotSurfacePreview() {
    MyApplicationTheme {
        PlotSurface()
    }
}

@Composable
fun Map(xPercent: Float, yPercent: Float, modifier: Modifier = Modifier) {
    // TODO fill out the square map here.
    //  and then create an inner Box composable
    //  with a clip shape of CircleShape.
    Box(
        modifier = modifier
            .size(300.dp)
            .background(Color.Blue)
    )
}

@Preview(showBackground = true)
@Composable
fun MapPreview() {
    MyApplicationTheme {
        Map(xPercent = 0.5f, yPercent = 0.5f)
    }
}
