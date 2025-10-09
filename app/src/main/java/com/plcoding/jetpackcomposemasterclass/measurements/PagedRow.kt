package com.plcoding.jetpackcomposemasterclass.measurements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

@Composable
fun PagedRow(
    page: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Layout(
        content = content, modifier = modifier
    ) { measurables, constraints ->


        /*
            - measurables are for child items
            - constraints come from parent
            - placeable refers to where to put in the screen which requires measurables
              to be placed according to constraints
         */
        val placeableList = measurables.map {
            /*
             The incoming minimum constraints in the video should be reset to 0.
             Otherwise, the children will be forced to stick to the parent constraints
             which can force them to fill the whole width when fillMaxWidth() is used.
             */
            it.measure(constraints.copy(minWidth = 0, minHeight = 0))
        }

        val pages =
            mutableListOf<List<Placeable>>() //list of currentpages i.e list of list of placeables
        var currentPage =
            mutableListOf<Placeable>() //only those placebales that fit into one page width
        var currentPageWidth = 0

        placeableList.fastForEach { placeable ->
            /*add the previous placebales to a new page list and add the new placeable to another list
                if they exceed the max width of the constraints
             */
            if (currentPageWidth + placeable.width > constraints.maxWidth) {
                pages.add(currentPage)
                currentPage = mutableListOf() //reset after adding the page
                currentPageWidth = 0
            }
            currentPage.add(placeable)
            currentPageWidth += placeable.width
        }
        //Most likely last page, Maybe it can be done better
        if (currentPage.isNotEmpty()) {
            pages.add(currentPage)
        }

        val pageItems = pages.getOrNull(page) ?: emptyList()

        layout(
            constraints.maxWidth, constraints.maxHeight
        ) { //getting width and height from the parent
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
private fun PagedRowPreview() {
    JetpackComposeMasterclassTheme {
        PagedRow(
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