package com.plcoding.jetpackcomposemasterclass.animations.exercises


import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme
import kotlin.math.sqrt

//from Philip's solution
data object TriangleShape: Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                moveTo(size.width / 2f, 0f)
                lineTo(0f, size.height)
                lineTo(size.width, size.height)
                close()
            }
        )
    }
}

@Composable
fun RotatingTriangles(
    modifier: Modifier = Modifier,
    width: Dp,
    colors: List<Color>,
) {
    val transition = rememberInfiniteTransition(
        label = "infinite transition"
    )
    val rotationValue by transition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = EaseInOut),
            repeatMode = RepeatMode.Restart,//RepeatMode.Repeat
        ), label = "rotation"
    )
    colors.forEachIndexed { index, color ->

        Box(modifier = modifier
            .size(width)
            .graphicsLayer {
                transformOrigin = TransformOrigin(0.5f, (2f / 3f))
                rotationZ = rotationValue * (index + 1)
            }
            /*
            Without clipping into custom Triangle Shape
            .size(width)
            .drawWithCache {
                val path = Path()
                path.moveTo(size.width / 2f, 0f)
                path.lineTo(0f, size.height)
                path.lineTo(size.width, size.height)
                path.close()
                onDrawBehind {
                    drawPath(
                        path = path,
                        color = color,
                    )
                }
            }*/
            .clip(TriangleShape)
            .background(color)
        )
    }

}


@Preview(showBackground = true)
@Composable
private fun RotatingTrianglePreview() {
    JetpackComposeMasterclassTheme {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(100.dp)
        ) {
            RotatingTriangles(
                modifier = Modifier.align(Alignment.Center),
                width = 20.dp,
                colors = listOf(
                    Color.Red,
                    Color.Green,
                    Color.Yellow,
                    Color.Magenta,
                    Color.Blue,
                    Color.Green,
                    Color.Cyan
                )
            )
        }

    }

}