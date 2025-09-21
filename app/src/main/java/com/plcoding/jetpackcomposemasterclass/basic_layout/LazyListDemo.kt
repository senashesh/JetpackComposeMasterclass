package com.plcoding.jetpackcomposemasterclass.basic_layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme
import kotlin.random.Random

@Composable
fun LazyListDemo(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(100) { i ->
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(Random.nextInt()))
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun FlexLayoutDemoPreview() {
    JetpackComposeMasterclassTheme {
        LazyListDemo()
    }
}