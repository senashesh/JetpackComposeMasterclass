package com.plcoding.jetpackcomposemasterclass.measurements.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

@Composable
fun OverflowLayout(
    isOverflowing: Boolean,
    modifier: Modifier = Modifier,
    mainContent: @Composable () -> Unit,
    overflowContent: @Composable () -> Unit,
) {
    SubcomposeLayout(
        modifier = modifier
    ) { constraints ->

        val mainPlaceable = subcompose("main", mainContent).first()
            .measure(constraints.copy(minWidth = 0, minHeight = 0))
        val overFlowPlaceable = if (isOverflowing) {
            subcompose("overflow", overflowContent).first()
                .measure(constraints.copy(minWidth = 0, minHeight = 0))
        } else null

        // Calculate final size
        val width = constraints.constrainWidth(
            maxOf(mainPlaceable.width, overFlowPlaceable?.width ?: 0)
        )
        val height = constraints.constrainHeight(
            mainPlaceable.height + (overFlowPlaceable?.height ?: 0)
        )

        layout(width, height) {
            // Place main at top
            mainPlaceable.place(0, 0)
            // Place overflow right below main
            overFlowPlaceable?.place(0, mainPlaceable.height)
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun OverFlowLayoutPreview() {
    JetpackComposeMasterclassTheme {
        var isOverflow by remember { mutableStateOf(false) }
        OverflowLayout(
            isOverflowing = isOverflow,
            modifier = Modifier.fillMaxWidth(),
            mainContent = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "This is a toggle Section", modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "test",
                        modifier = Modifier.clickable(
                            onClick = { isOverflow = !isOverflow }))

                }

            },
            overflowContent = {

                Text(
                    text = "Secret Content",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Yellow)
                        .padding(16.dp)
                )

            })


    }
}