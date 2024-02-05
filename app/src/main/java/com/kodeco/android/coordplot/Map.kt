package com.kodeco.android.coordplot

import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kodeco.android.coordplot.ui.theme.MyApplicationTheme

@Composable
fun Map(
    xPercent: Float,
    yPercent: Float,
    dotSize: Int,
    dotColor: Color,
    dotImageResId: Int,
    plotSize: Int,
    plotColor: Color,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current.density
    var boxSize by remember { mutableStateOf(Size(0, 0)) }

    Box(
        modifier = modifier
            .size(plotSize.dp)
            .aspectRatio(1f) // Ensure the plot is a square (1:1 aspect ratio)
            .background(plotColor)
            .onGloballyPositioned { coordinates ->
                boxSize = Size(
                    ((coordinates.size.width / density).toInt()),
                    ((coordinates.size.height / density).toInt())
                )
            }
    ) {
        val dotRadius = dotSize / 2
        val offsetX = (xPercent * boxSize.width)
        val offsetY = (yPercent * boxSize.height)

        Image(
            painter = painterResource(id = dotImageResId),
            contentDescription = "Plot dot image",
            colorFilter = ColorFilter.tint(dotColor),
            modifier = Modifier
                .offset(
                    x = (offsetX - dotRadius).dp,
                    y = (offsetY - dotRadius).dp
                )
                .size(dotSize.dp)
                .clip(CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapPreview() {
    MyApplicationTheme {
        Map(
            xPercent = 0.5f,
            yPercent = 0.5f,
            dotSize = 10,
            dotColor = Color.White,
            dotImageResId = R.drawable.graph_icon,
            plotSize = 100,
            plotColor = Color.Blue
        )
    }
}