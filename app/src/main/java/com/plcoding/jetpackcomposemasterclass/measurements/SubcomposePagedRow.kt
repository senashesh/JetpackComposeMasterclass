package com.plcoding.jetpackcomposemasterclass.measurements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMaxOfOrNull
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

/*  MainActivity Code inside Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


     var page by remember {
                        mutableIntStateOf(0)
                    }
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                    ) {
                        SubcomposePagedRow(
                            page = page,
                            modifier = Modifier
                                .background(Color.Red)
                        ) {
                            (1..1000).forEach { _ ->
                                Box(
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(Random.nextInt(300).dp)
                                        .background(Color(Random.nextInt()))
                                )
                            }
                        }
                        Button(onClick = {
                            page++
                        }) {
                            Text("Go to next page")
                        }
                    }
 */

// LayoutComposable: Measure children -> measure the layout itself -> place the children
//SubcomposeLayout: Measure children -> subcompose -> measure the layout itself -> place the children

@Composable
fun SubcomposePagedRow(
    page: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    SubcomposeLayout(
        modifier = modifier
    ) { constraints -> //notice no measurables only constraints coming from parent

        val pages = mutableListOf<List<Placeable>>()
        var currentPage = mutableListOf<Placeable>()
        var currentPageWidth = 0

        val measurables = subcompose("content", content) //a function available in subcompose layout

        for(measurable in measurables) {
            val placeable = measurable.measure(constraints.copy(minWidth = 0, minHeight = 0))

            if(currentPageWidth + placeable.width > constraints.maxWidth) {
                if(pages.size == page) {
                    break //dont measure anymore composables for efficiency and break out
                }
                pages.add(currentPage)
                currentPage = mutableListOf()
                currentPageWidth = 0
            }
            currentPage.add(placeable)
            currentPageWidth += placeable.width
        }

        if(currentPage.isNotEmpty()) {
            pages.add(currentPage)
        }

        val pageItems = pages.getOrNull(page) ?: emptyList()
        val maxHeight = pageItems.fastMaxOfOrNull { it.height } ?: 0
        //maxHeight of the composables is used instead of constraints.maxHeight to only take required height
        layout(constraints.maxWidth, maxHeight) {
            var xOffset = 0
            pageItems.fastForEach { placeable ->
                placeable.place(xOffset, 0)
                xOffset += placeable.width
            }
        }
    }
}

@Preview
@Composable
private fun SubComposePagedRowPreview() {
    JetpackComposeMasterclassTheme {
        SubcomposePagedRow(
            page = 0
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(150.dp)
                    .background(Color.Yellow)
            )
            Box(
                modifier = Modifier
                    .width(75.dp)
                    .height(100.dp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .background(Color.Blue)
            )
        }
    }
}