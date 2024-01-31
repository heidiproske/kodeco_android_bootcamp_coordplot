package com.kodeco.android.coordplot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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

// Constants
private const val PLOT_WIDTH = 300
private val PLOT_BACKGROUND_COLOR = Color.Blue
private val SLIDER_COLOR = Color.DarkGray

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
            MapSlider(value = axisXValue, onValueChange = { axisXValue = it })
            MapSlider(value = axisYValue, onValueChange = { axisYValue = it })
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
            .size(PLOT_WIDTH.dp)
            .background(PLOT_BACKGROUND_COLOR)
    )
}

@Preview(showBackground = true)
@Composable
fun MapPreview() {
    MyApplicationTheme {
        Map(xPercent = 0.5f, yPercent = 0.5f)
    }
}

@Composable
fun MapSlider(value: Float, onValueChange: (Float) -> Unit, sliderColor: Color = SLIDER_COLOR) {
    val colors = SliderDefaults.colors(
        thumbColor = sliderColor,
        activeTrackColor = sliderColor,
        inactiveTrackColor = sliderColor
    )
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = 0f..1f,
        colors = colors,
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun MapSliderPreview() {
    MapSlider(value = 0.3f, onValueChange = { /* no-op */ })
}

