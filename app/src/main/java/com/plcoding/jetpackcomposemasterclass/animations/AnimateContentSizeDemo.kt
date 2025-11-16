package com.plcoding.jetpackcomposemasterclass.animations

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

@Composable
fun AnimateContentSizeDemo(modifier: Modifier = Modifier) {
    var toggle by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                toggle = !toggle
            }
        ) {
            Text("Toggle")
        }

        Box(
            modifier = Modifier
                .background(Color.Blue)
                .animateContentSize()
                //always put animateContentSize before defining dimensions
                .height(if (toggle) 400.dp else 200.dp)
                .fillMaxWidth()
                //don't put here animateContentSize()
        )
        Text(
            text = "I'm below",
            modifier = Modifier
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun AnimateContentSizeDemoPreview() {
    JetpackComposeMasterclassTheme {
        AnimateContentSizeDemo()
    }
}