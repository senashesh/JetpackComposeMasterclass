package com.plcoding.jetpackcomposemasterclass.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun AnimatableApiDemo(modifier: Modifier = Modifier) {
    var size by remember {
        mutableStateOf(IntSize.Zero) //size of the outerbox
    }
    val step1Offset = remember {
        Animatable(
            initialValue = 0f,
        )
    }
    val step2Offset = remember {
        Animatable(
            initialValue = 0f,
        )
    }
    //for animating composables it is ok to have coroutine as a side effect
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) //makes a sqare with the height equal to the fillMaxWidth
                .onSizeChanged {
                    size = it
                }
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset {
                        IntOffset(
                            //move to the end of the composable in both directions
                            x = ((size.width - 100.dp.roundToPx()) * step1Offset.value).roundToInt(),
                            y = ((size.height - 100.dp.roundToPx()) * step1Offset.value).roundToInt()
                        )
                    }
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset {
                        IntOffset(
                            x = ((size.width - 100.dp.roundToPx()) * step2Offset.value).roundToInt(),
                            y = ((size.height - 100.dp.roundToPx()) * step1Offset.value).roundToInt()
                        )
                    }
                    .graphicsLayer { //use graphics layer for transform, scale, rotate or alpha change
                        val scale = 1 - (step2Offset.value * 0.25f) //75% of the size
                        scaleX = scale
                        scaleY = scale
                    }
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .offset {
                        IntOffset(
                            x = ((size.width - 100.dp.roundToPx()) * step1Offset.value).roundToInt(),
                            y = ((size.height - 100.dp.roundToPx()) * step2Offset.value).roundToInt()
                        )
                    }
                    .graphicsLayer {
                        val scale = 1 - (step2Offset.value * 0.5f) //50% of the size
                        scaleX = scale
                        scaleY = scale
                    }
                    .background(Color.Blue)
            )
        }
        Button(
            onClick = {
                //animateTo requires to launch in a coroutine which allows combining animations
                scope.launch {
                    //if you want animations to run same time instead of sequential as show here use multiple launch
                    step1Offset.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = 5000,
                            easing = EaseInOutCubic
                        )
                    )
                    step2Offset.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = 5000,
                            easing = EaseInOutCubic
                        )
                    )
                }
            }
        ) {
            Text("Start")
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun AnimatableApiDemoPreview() {
    JetpackComposeMasterclassTheme {
        AnimatableApiDemo()
    }
}