package com.plcoding.jetpackcomposemasterclass.basic_layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowLayoutDemo(modifier: Modifier = Modifier) {
    FlowRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        maxLines = 4,
        overflow = FlowRowOverflow.expandOrCollapseIndicator(expandIndicator = {
            IconButton(
                onClick = {}) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Expand"
                )
            }
        }, collapseIndicator = {
            IconButton(
                onClick = {}) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Expand"
                )
            }
        })) {
        for (i in 1..30) {
            AssistChip(onClick = {}, label = {
                Text("Item $i")
            })
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun FlexLayoutDemoPreview() {
    JetpackComposeMasterclassTheme {
        FlowLayoutDemo()
    }
}