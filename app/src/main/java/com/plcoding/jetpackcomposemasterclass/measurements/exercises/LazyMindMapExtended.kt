package com.plcoding.jetpackcomposemasterclass.measurements.exercises

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.round
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMapIndexedNotNull
import kotlin.math.min

/*
    MainActivity Code
    setContent {
            JetpackComposeMasterclassTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    val mindMapItems = remember {
                        listOf(
                            MindMapItem(
                                title = "Hello world 1",
                                percentageOffset = Offset(
                                    x = 0f,
                                    y = 0f
                                )
                            ),
                            MindMapItem(
                                title = "Hello world 2",
                                percentageOffset = Offset(
                                    x = 1f,
                                    y = 0f
                                )
                            ),
                            MindMapItem(
                                title = "Hello world 3",
                                percentageOffset = Offset(
                                    x = 0.3f,
                                    y = -0.5f
                                )
                            ),
                            MindMapItem(
                                title = "Hello world 4",
                                percentageOffset = Offset(
                                    x = -0.2f,
                                    y = 1.5f
                                )
                            ),
                        )
                    }

                    var mindMapOffset by remember {
                        mutableStateOf(IntOffset.Zero)
                    }
                    LazyMindMap(
                        items = mindMapItems,
                        mindMapOffset = mindMapOffset,
                        onDrag = { delta ->
                            mindMapOffset += delta
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
fun MindMapToDo(title: String = "MindMap ToDo",
                description:String = "Description",
                modifier: Modifier = Modifier) {
    var isChecked by remember { mutableStateOf(false) }
    Row(modifier = modifier
        .fillMaxWidth()
        .border(width = 1.dp,color = Color.Black)
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier =Modifier
            .weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = description)

        }
        Checkbox(checked = isChecked,
            onCheckedChange = {isChecked = !isChecked})
    }
}

@Composable
fun IncDecCompose(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Column(modifier = modifier
        .fillMaxWidth()
        .border(width = 1.dp,color = Color.Black)
        .padding(16.dp),
       horizontalAlignment = Alignment.CenterHorizontally ) {
        Text(text = "$count", fontWeight = FontWeight.Bold, fontSize = 34.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = {count++}) {
                Text(text = "Inc")
            }
            Button(onClick = {count--}) {
                Text(text = "Dec")
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun MindMapToDoPreview() {
    JetpackComposeMasterclassTheme {
        Box(modifier = Modifier.padding(16.dp)){
            MindMapToDo()
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun IncDecComposePreview() {
    JetpackComposeMasterclassTheme {
        Box(modifier = Modifier.padding(16.dp)){
            IncDecCompose()
        }
    }
}

 */

data class ExtMindMapItem( //each item
    val content: @Composable () -> Unit = {},
    val itemConstraints: ConstraintsInDP,
    val offset: DpOffset
)

data class ConstraintsInDP(
    val maxWidth: Dp,
    val maxHeight: Dp,
)

private data class ExtProcessedMindMapItem(
    val placeable: Placeable,
    val finalXPosition: Int,
    val finalYPosition: Int,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyMindMapExtended(
    items: List<ExtMindMapItem>,
    mindMapOffset: IntOffset = IntOffset.Zero, //for the whole layout
    onDrag: (delta: IntOffset) -> Unit, //for the whole layout
    itemModifier: Modifier = Modifier, //for each item
    modifier: Modifier = Modifier, //for the layout
) {

    LazyLayout(
        modifier = modifier.draggable2D(
            state = rememberDraggable2DState { delta ->
                onDrag(delta.round())
            }), itemProvider = { //LazyLayout needs an itemProvider
            object : LazyLayoutItemProvider {
                override val itemCount: Int
                    get() = items.size

                @Composable
                override fun Item(index: Int, key: Any) {
                    items[index].content()
                }
            }
        }) { constraints ->
        
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight

        val visibleArea = IntRect(
            left = 0, top = 0, right = layoutWidth, bottom = layoutHeight
        ) // Total Layout

        val visibleItems = items.fastMapIndexedNotNull { index, item ->
            val finalXPosition =
                (item.offset.x.roundToPx() + layoutWidth / 2 + mindMapOffset.x)
            val finalYPosition =
                (item.offset.y.roundToPx() + layoutHeight / 2 + mindMapOffset.y)

            val maxItemWidth = item.itemConstraints.maxWidth.roundToPx()
            val maxItemHeight = item.itemConstraints.maxHeight.roundToPx()

            // creating extra space
            val extendedItemBounds = IntRect(
                left = finalXPosition - maxItemWidth / 2,
                top = finalYPosition - maxItemHeight / 2,
                right = finalXPosition + 3 * (maxItemWidth / 2),
                bottom = finalYPosition + 3 * (maxItemHeight / 2)
            )

            if (visibleArea.overlaps(extendedItemBounds)) {
                val placeable = measure(
                    index = index, constraints = Constraints(
                        minWidth = 0,
                        minHeight = 0,
                        // Use max sizes so fillMaxWidth/weight can resolve.
                        // Otherwise it can cause children to collapse to 0.
                        maxWidth = min(item.itemConstraints.maxWidth.roundToPx(), layoutWidth),
                        maxHeight = min(item.itemConstraints.maxHeight.roundToPx(), layoutHeight)
                    )
                ).first()
                ExtProcessedMindMapItem(
                    placeable = placeable,
                    finalXPosition = finalXPosition,
                    finalYPosition = finalYPosition
                )
            } else {
                null
            }
        }

        println("Visible item count: ${visibleItems.size}")

        layout(constraints.maxWidth, constraints.maxHeight) {
            visibleItems.fastForEach { item ->
                item.placeable.place(
                    x = item.finalXPosition - item.placeable.width / 2,
                    y = item.finalYPosition - item.placeable.height / 2
                )
            }
        }
    }
}