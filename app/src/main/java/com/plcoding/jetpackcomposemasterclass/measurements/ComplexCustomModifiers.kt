package com.plcoding.jetpackcomposemasterclass.measurements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.layout
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateMeasurement
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

/*
    Make it composable to access LocalDensity.current
    Modifiers can also be composable function

    Caveats:
    - LocalDensity or any Local values can lead to consistency in certain composables covered later in the course
    - If a compose returns a compose then, it is always recomposed regardless of state changes
 */
@Composable
fun Modifier.negativePadding(horizontal: Dp): Modifier {
    val density = LocalDensity.current
    val px = with(density) {
        horizontal.roundToPx()
    }
    //layout function is accessible inside Modifiers.layout
    return layout { measurable, constraints ->
        val placeable = measurable.measure(
            constraints = constraints.copy(
                //increase the width of the parent constraint to negate the horizontal padding
                minWidth = constraints.minWidth + 2 * px,
                maxWidth = constraints.maxWidth + 2 * px
            )
        )

        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}

//Better Way for negative padding
fun Modifier.improvedNegativePadding(horizontal: Dp): Modifier {
    return this.then(NegativePaddingElement(horizontal))
}

data class NegativePaddingElement(
    private val horizontal: Dp
): ModifierNodeElement<NegativePaddingNode>() {

    override fun create(): NegativePaddingNode {
        return NegativePaddingNode(horizontal)
    }

    override fun update(node: NegativePaddingNode) {
        //when the value of the horizontal padding changes dynamically
        node.horizontal = horizontal
    }
}

class NegativePaddingNode(
    var horizontal: Dp
    //Modifier nodes can be of many types, we are using LayoutModifierNode here
    //see: https://developer.android.com/develop/ui/compose/custom-modifiers
): LayoutModifierNode, Modifier.Node(), CompositionLocalConsumerModifierNode {

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val density = currentValueOf(LocalDensity) //we access this from CompositionLocalConsumerModifierNode
        val px = with(density) {
            horizontal.roundToPx()
        }

        val placeable = measurable.measure(
            constraints = constraints.copy(
                minWidth = constraints.minWidth + 2 * px,
                maxWidth = constraints.maxWidth + 2 * px
            )
        )

        return layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}

@Composable
fun MyList(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        //just a state to demonstrate dynamic value changes in negative horizontal dp
        var toggle by remember {
            mutableStateOf(true)
        }

        Text(
            text = "This is item 1",
            modifier = Modifier
                .clickable {
                    toggle = !toggle
                }
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .improvedNegativePadding(
                    //change the negative horizontal dp based on state
                    if(toggle) 16.dp else 32.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                //.negativePadding(16.dp) // old way not efficient since compose modifier is used
                .improvedNegativePadding(16.dp)
        )
        Text(
            text = "This is another item",
            modifier = Modifier
                .clickable {

                }
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                //.negativePadding(16.dp) // old way not efficient since compose modifier is used
                .improvedNegativePadding(16.dp)
        )
        Text(
            text = "And another one",
            modifier = Modifier
                .clickable {

                }
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(
                onClick = {}
            ) {
                Text("Button 1")
            }
            Button(
                onClick = {}
            ) {
                Text("Button 2")
            }
            Button(
                onClick = {}
            ) {
                Text("Button 3")
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun MyListPreview() {
    JetpackComposeMasterclassTheme {
        MyList()
    }
}