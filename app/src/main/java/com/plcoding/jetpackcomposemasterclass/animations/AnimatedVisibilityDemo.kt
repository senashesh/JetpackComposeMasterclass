package com.plcoding.jetpackcomposemasterclass.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

@Composable
fun AnimatedVisibilityDemo(modifier: Modifier = Modifier) {
    var toggle by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = {
                toggle = !toggle
            }
        ) {
            Text("Toggle")
        }
        val easing = EaseInExpo
        AnimatedVisibility(
            visible = toggle,
            //easing determines which particular time which animation plays how fast
            enter =
                /*
                    --Tween animation spec--
                    scaleIn(
                        tween(
                            durationMillis = 1000,
                            delayMillis = 300, //after how long animation starts
                            easing = LinearEasing
                        )
                    )
                    --Spring animation spec--
                    scaleIn(
                        spring(
                            dampingRatio = Spring.DampingRatioHighBouncy
                        )
                    )
                 */
                scaleIn(
                animationSpec = keyframes {
                    //custom animationSpec that allows us to control which animation to play at different frames
                    durationMillis = 5000
                    0.75f at 2500 using EaseInExpo //scales to 75 perc at 2500 time
                    0.25f at 3750 using LinearEasing
                    1f at 5000 using FastOutSlowInEasing
                }
            ), //+ fadeIn(), //comine different animations
            exit = scaleOut(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy
                )
            )
        ) {
            Text(
                text = "Hello world",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp)
                    .border(
                        width = 5.dp,
                        color = Color.Red
                    )
                    .wrapContentSize()
            )
        }
        Text("Hello world")
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun AnimatedVisibilityDemoPreview() {
    JetpackComposeMasterclassTheme {
        AnimatedVisibilityDemo()
    }
}