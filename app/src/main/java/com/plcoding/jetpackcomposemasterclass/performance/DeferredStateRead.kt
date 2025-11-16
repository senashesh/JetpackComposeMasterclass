package com.plcoding.jetpackcomposemasterclass.performance

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.launch

@Composable
fun DeferredStateReads(modifier: Modifier = Modifier) {
    val state = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val fabOffset by remember {
        /* derivedStateOf is used when the input value in this case the
            state.firstVisibleIndex is changing more often then the derived value needed
            - here fabOffset
         */
        derivedStateOf {
            val percentage = (1f - (state.firstVisibleItemIndex / 10f))
            (percentage * 100.dp).coerceIn(
                minimumValue = 0.dp,
                maximumValue = 100.dp
            )
        }
    }
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            MovingFloatingActionButton(
                offset = { fabOffset },
                onClick = {
                    scope.launch {
                        state.animateScrollToItem(0)
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            state = state,
            modifier = modifier
                .fillMaxSize(),
            contentPadding = innerPadding
        ) {
            items(100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun MovingFloatingActionButton(
    /*
        By putting the offset in a lambda that returns Dp instead of just passing Dp
        we make sure the lambda is called in the layout phases instead of composition
        if it had only Dp value instead that constantly changes causing unnecessary recomposition.
     */
    offset: () -> Dp,  //Dp
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .offset {
                //also here by passing lambda instead of value we are avoiding unnecessary recomposition
                IntOffset(
                    x = 0,
                    y = with(density) { offset().roundToPx() }
                )
            },
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = "Scroll to top"
        )
    }
}