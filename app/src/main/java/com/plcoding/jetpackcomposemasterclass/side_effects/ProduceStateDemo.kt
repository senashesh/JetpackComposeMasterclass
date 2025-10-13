package com.plcoding.jetpackcomposemasterclass.side_effects

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun ProduceStateDemo(modifier: Modifier = Modifier) {
    /*
        ProduceState is basically a LaunchedEffect under the hood.
        It allows to observe a local state which values needs to be changed inside of a suspending function.
        Here we need to call delay for the counter.
        However, such cases are better managed inside of a viewmodel that has its own coroutinescope'
        and we don't need to manage it inside of a UI
     */

    val counter by produceState(0) {
        while(true) {
            delay(1000L)
            value += 1
        }
    }
    Text(
        text = counter.toString(),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun ProduceStateDemoPreview() {
    ProduceStateDemo()
}