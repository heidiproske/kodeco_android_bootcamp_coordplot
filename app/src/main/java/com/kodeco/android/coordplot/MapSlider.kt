package com.kodeco.android.coordplot

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Constants
private const val TEXT_MAX_WIDTH = 120
private const val TEXT_START_PADDING = 8

@Composable
fun MapSlider(
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    sliderColor: Color,
    modifier: Modifier = Modifier
) {
    val colors = SliderDefaults.colors(
        thumbColor = sliderColor,
        activeTrackColor = sliderColor,
        inactiveTrackColor = sliderColor
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$title: ${"%.0f".format(value * 100)}%",
            modifier = Modifier
                .padding(start = TEXT_START_PADDING.dp)
                .width(TEXT_MAX_WIDTH.dp)
        )

        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..1f,
            colors = colors,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapSliderPreview() {
    MapSlider(
        title = "X",
        value = 0.3f,
        onValueChange = { /* no-op */ },
        sliderColor = Color.Green
    )
}