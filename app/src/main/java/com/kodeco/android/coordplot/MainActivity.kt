package com.kodeco.android.coordplot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
            Map(
                xPercent = axisXValue,
                yPercent = axisYValue,
                dotSize = DOT_DIAMETER,
                dotColor = DOT_BACKGROUND_COLOR,
                dotImageResId = R.drawable.graph_icon,
                plotSize = PLOT_SIZE,
                plotColor = PLOT_BACKGROUND_COLOR
            )

            // Ensure that the dot will never overlap or touch the top slider
            Spacer(modifier = Modifier.height(DOT_DIAMETER.dp))

            MapSlider(
                value = axisXValue,
                onValueChange = { axisXValue = it },
                sliderColor = SLIDER_COLOR
            )
            MapSlider(
                value = axisYValue,
                onValueChange = { axisYValue = it },
                sliderColor = SLIDER_COLOR
            )
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
