package com.plcoding.jetpackcomposemasterclass.basics_modifiers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.R
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme
import kotlin.math.PI
import kotlin.math.tan

@Composable
fun ShapeModifiersDemo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.kermit),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
           /* .clip(
                RoundedCornerShape(
                    topStartPercent = 50
                    //topStart = 50f //its on pixels
                )
            )*/
            //.clip(TriangleShape)
            .size(200.dp) //make it a square first
            .clip(StarShape)

    )
}

data object TriangleShape: Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = Path().apply {
                moveTo(
                    x = size.width / 2f,
                    y = 0f
                )
                lineTo(
                    x = 0f,
                    y = size.height
                )
                lineTo(
                    x = size.width,
                    y = size.height
                )
                close()
            }
        )
    }
}

fun Int.toRadian() = this * PI / 180

object StarShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {

        val width = size.width
        val height = size.height

        val pointOne = Pair(width / 2, 0f)
        val pointTwo = Pair((width / 2) - (height * tan(18.toRadian())), height)
        val pointThree = Pair(width, height - ((width + 2 * height * tan(18.toRadian())) / (2 * tan(54.toRadian()))))
        val pointFour = Pair(0f, height - ((width + 2 * height * tan(18.toRadian())) / (2 * tan(54.toRadian()))))
        val pointFive = Pair((width / 2 + height * tan(18.toRadian())), height)

        return Outline.Generic(
            path = Path().apply {
                moveTo(
                    x = pointOne.first,
                    y = pointOne.second
                )
                lineTo(
                    x = pointTwo.first.toFloat(),
                    y = pointTwo.second
                )
                lineTo(
                    x = pointThree.first,
                    y = pointThree.second.toFloat()
                )
                lineTo(
                    x = pointFour.first.toFloat(),
                    y = pointFour.second.toFloat()
                )
                lineTo(
                    x = pointFive.first.toFloat(),
                    y = pointFive.second
                )
                close()
            }
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ShapeModifiersDemoPreview() {
    JetpackComposeMasterclassTheme {
        ShapeModifiersDemo()
    }
}