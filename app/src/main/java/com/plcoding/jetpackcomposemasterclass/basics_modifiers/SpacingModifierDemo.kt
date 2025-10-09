package com.plcoding.jetpackcomposemasterclass.basics_modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

/*
    Window insets are needed for system bars and navigation bars.
    It is best to use innerpadding  inside scaffold to deal with this.
    In special cases you might have to play around with various alternatives
    for eg in Modifiers properties: statusbarPadding,navigationBarspadding,
    safeDrawingPadding,  safeGesturesPadding if you are not using scaffold.

    If using scaffold then in Main activity add following code
     setContent {
            JetpackComposeMasterclassTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.safeGestures
                ) { innerPadding ->
                    SpacingModifierDemo(
                        modifier = Modifier
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                    )
                }
            }
        }
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SpacingModifierDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color.Red)
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .imeNestedScroll(), //last item of the list is displayed while keyboard scrolls up
        ) {
            items(100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.imePadding()//keyboard scrolls up and last item on the list is displayed (needed both)
        )
    }
}

@Preview
@Composable
private fun SpacingModifierDemoPreview() {
    JetpackComposeMasterclassTheme {
        SpacingModifierDemo()
    }
}