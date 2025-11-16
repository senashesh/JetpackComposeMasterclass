package com.plcoding.jetpackcomposemasterclass.animations.exercises

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme
import java.util.Locale

@Composable
fun ValueScreen(modifier: Modifier = Modifier) {
    var value by remember {
        mutableFloatStateOf(0f)
    }

    val animatedValue by animateFloatAsState(value, animationSpec = tween(2000))

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ValueDisplay(
            value = animatedValue,
            maxValue = 100f,
            unit = "%",
            color = Color.Red,
            modifier = Modifier.wrapContentSize(),
            strokeWidth = 10.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                value = 10f
            }) {
                Text(text = "10%")
            }
            Button(onClick = {
                value = 50f
            }) {
                Text(text = "50%")
            }
            Button(onClick = {
                value = 90f
            }) {
                Text(text = "90%")
            }
        }
    }


}

@Composable
fun ValueDisplay(
    value: Float,
    maxValue: Float,
    unit: String,
    color: Color = Color.Red,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 10.dp,
) {

    Box(modifier = modifier
        .size(200.dp)
        .padding(10.dp)
        .drawBehind {
            drawArc(
                color = color,
                startAngle = -90f, //starting at the top
                sweepAngle = (value / maxValue) * 360f, // percentage to draw
                useCenter = false, // avoid slicing
                style = Stroke(width = strokeWidth.toPx())
            )
        }
        ) {
        Text(
            //Forcing locale to use decimal instead of comma
            text = "${String.format(Locale.US, "%.1f", value)}$unit",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ValueDisplayPreview() {
    JetpackComposeMasterclassTheme {
        ValueScreen()
    }
}