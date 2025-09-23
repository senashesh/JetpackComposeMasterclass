package com.plcoding.jetpackcomposemasterclass.state_management

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

//Originally named Counter in old git commit in philip's git
@Composable
fun Counter(modifier: Modifier = Modifier) {

    /*
        remember can only be called inside composable, without remember when the compose recomposes due to other
        state changes then the state could be reset.
        rememberSaveable is for remember state across device reconfiguration
        when compose compares for remembering then it checks using the equalTo, so in mutableList,
        if we only add items then the list will always be equal to each other so recomposition won't happen
     */
    var items by rememberSaveable {
        mutableStateOf(
            listOf<String>(
                "Item"
            )
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                items += "Item" //adding new list by copying the new one and adding item instead of adding items only
            }) {
            Text("Add item")
        }
        Text(
            text = items.toString(), modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CounterPreview() {
    JetpackComposeMasterclassTheme {
        Counter()
    }
}