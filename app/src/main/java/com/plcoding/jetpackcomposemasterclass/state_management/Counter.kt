package com.plcoding.jetpackcomposemasterclass.state_management

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

private const val BASE_URL = "https://..."
private var counter by mutableIntStateOf(0)

/*
    - In UDF (Unidirectional Data Flow) states are passed down while events bubble up
    - Stateless composable is a composable that doesn't hold any state.
    - Stateleful composable is a composable that holds a state for eg: See ItemAdd.kt and are self contained
    - Preferably, state should be passed as much higher up in the hierarchy of composables as possible.
    - To make a stateleful composable stateless i.e. State Hoisting, there are two things:
        i. pass in the state as parameter,
        ii. pass in the event also as a parameter.
    - See also StateHoisting.kt
 */
@Composable
fun Counter(
    counter: Int,
    onCounterButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onCounterButtonClick
        ) {
            Text("Count: $counter")
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CounterPreview() {
    JetpackComposeMasterclassTheme {
        Counter(
            counter = 0, onCounterButtonClick = {})
    }
}