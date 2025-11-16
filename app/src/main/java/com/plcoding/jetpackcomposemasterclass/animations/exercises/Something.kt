package com.plcoding.jetpackcomposemasterclass.animations.exercises



/*


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.min

@Composable
fun PercentageArcScreen() {
    var percent by remember { mutableIntStateOf(50) }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(8.dp))

            // Center area with the text and the arc around it
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                PercentageArc(
                    percent = percent,
                    boxSize = 220.dp,
                    strokeWidth = 10.dp,
                    color = Color.Red
                )
            }

            // Buttons at the bottom
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
            ) {
                Button(onClick = { percent = 50 }) { Text("50%") }
                Button(onClick = { percent = 80 }) { Text("80%") }
                Button(onClick = { percent = 100 }) { Text("100%") }
            }
        }
    }
}

@Composable
private fun PercentageArc(
    percent: Int,
    boxSize: Dp,
    strokeWidth: Dp = 10.dp,
    color: Color = Color.Red,
) {
    val clamped = percent.coerceIn(0, 100)

    Box(
        modifier = Modifier.size(boxSize),
        contentAlignment = Alignment.Center
    ) {
        // Draw the arc/circle
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokePx = strokeWidth.toPx()
            // Prevent clipping by drawing inside an inset equal to half the stroke width
            inset(strokePx / 2f) {
                if (clamped >= 100) {
                    drawCircle(
                        color = color,
                        style = Stroke(width = strokePx)
                    )
                } else if (clamped > 0) {
                    val sweep = (clamped / 100f) * 360f
                    drawArc(
                        color = color,
                        startAngle = -90f, // start at 12 o'clock
                        sweepAngle = sweep,
                        useCenter = false,
                        style = Stroke(width = strokePx)
                    )
                }
            }
        } else if (clamped > 0) {
        val sweep = (clamped / 100f) * 360f
        drawArc(
            color = color,
            startAngle = -90f, // start at 12 o'clock
            sweepAngle = sweep,
            useCenter = false,
            style = Stroke(width = strokePx)
        )
    }
        // If clamped == 0, draw nothing (no arc)
    }

    // Percentage text on top
    Text(
        text = "$clamped%",
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold
    )
}
}

@Preview(showBackground = true)
@Composable
private fun PercentageArcScreenPreview() {
    MaterialTheme {
        PercentageArcScreen()
    }
}
*/
