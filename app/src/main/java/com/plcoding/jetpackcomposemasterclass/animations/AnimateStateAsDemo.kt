package com.plcoding.jetpackcomposemasterclass.animations

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

@Composable
fun AnimateStateAsDemo(modifier: Modifier = Modifier) {
    var toggle by remember {
        mutableStateOf(false)
    }
    /*
        Fire-and-forget animation function for Float. This Composable function is overloaded for
        different parameter types such as Dp, Color, Offset, etc. When the provided targetValue is
        changed, the animation will run automatically. If there is already an animation in-flight
        when targetValue changes, the on-going animation will adjust course to animate towards
        the new target value. animateFloatAsState returns a State object. The value of the state
         object will continuously be updated by the animation until the animation finishes.
     */
    val ratio by animateFloatAsState(
        targetValue = if(toggle) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        ),
        label = "ratio animation"
    )
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    /*
                        when it comes to translation, transformation, rotation, alpha value it
                        is recommened to use graphicsLayer modifier. it manipulates the underlying
                        canvas of the composable
                     */
                    .graphicsLayer {
                        rotationZ = 405f * ratio //360 + 45
                        scaleX = 1f - (ratio * 0.5f) //50 perc by default
                        scaleY = 1f - (ratio * 0.5f)
                    }
                    .size(100.dp)
                    .background(Color.Red)
            )
        }
        Button(
            onClick = {
                toggle = !toggle
            }
        ) {
            Text("Toggle")
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun AnimateStateAsDemoPreview() {
    JetpackComposeMasterclassTheme {
        AnimateStateAsDemo()
    }
}