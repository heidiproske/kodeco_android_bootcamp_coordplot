package com.kodeco.android.coordplot

import android.os.Bundle
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.ui.theme.MyApplicationTheme

// Constants
private const val DOT_DIAMETER = 36
// Breathing room around the plot, ensuring the dot never touches sides of screen.
private const val PLOT_PADDING = DOT_DIAMETER / 2 + 4
private const val PLOT_SIZE = 300 // Preferred size of the square grid to draw the plot
private val PLOT_BACKGROUND_COLOR = Color.Blue
private const val SLIDERS_STARTING_POINT = 0.5f
private val DOT_BACKGROUND_COLOR = Color.Green
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

// Builds out a plot surface that the user can interact with to move a dot on a plot
// using 2 sliders representing the X & Y axis.
@Composable
fun PlotSurface() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        var axisXValue: Float by rememberSaveable { mutableFloatStateOf(SLIDERS_STARTING_POINT) }
        var axisYValue: Float by rememberSaveable { mutableFloatStateOf(SLIDERS_STARTING_POINT) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PLOT_PADDING.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Map(xPercent = axisXValue, yPercent = axisYValue)

            // Ensure that the dot will never overlap or touch the top slider
            Spacer(modifier = Modifier.height(DOT_DIAMETER.dp))

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
    val density = LocalDensity.current.density
    var boxSize by remember { mutableStateOf(Size(0, 0)) }

    Box(
        modifier = modifier
            .size(PLOT_SIZE.dp)
            .aspectRatio(1f) // Ensure the plot is a square (1:1 aspect ratio)
            .background(PLOT_BACKGROUND_COLOR)
            .onGloballyPositioned { coordinates ->
                boxSize = Size(
                    ((coordinates.size.width / density).toInt()),
                    ((coordinates.size.height / density).toInt())
                )
            }
    ) {
        val dotRadius = DOT_DIAMETER / 2
        val offsetX = (xPercent * boxSize.width)
        val offsetY = (yPercent * boxSize.height)

        Box(
            modifier = modifier
                .offset(
                    x = (offsetX - dotRadius).dp,
                    y = (offsetY - dotRadius).dp
                )
                .size(DOT_DIAMETER.dp)
                .clip(CircleShape)
                .background(DOT_BACKGROUND_COLOR)
        )
    }
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

