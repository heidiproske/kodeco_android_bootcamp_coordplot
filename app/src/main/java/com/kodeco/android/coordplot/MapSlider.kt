package com.kodeco.android.coordplot

import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MapSlider(value: Float, onValueChange: (Float) -> Unit, sliderColor: Color) {
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
    MapSlider(value = 0.3f, onValueChange = { /* no-op */ }, sliderColor = Color.Green)
}