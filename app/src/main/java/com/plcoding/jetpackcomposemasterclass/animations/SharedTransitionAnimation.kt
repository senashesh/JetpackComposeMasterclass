package com.plcoding.jetpackcomposemasterclass.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.jetpackcomposemasterclass.R
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionAnimation(modifier: Modifier = Modifier) {
    //controls which item you want to be visible and invisible and therefore show animation in between
    var isExpanded by remember {
        mutableStateOf(false)
    }
    /* It transitions one form of layout into different form of layout
        - Wrap the composables that are affected by the animation into SharedTransitionLayout

     */

    SharedTransitionLayout(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                //animatedVisibility requires a column scope
                this@Column.AnimatedVisibility(
                    visible = !isExpanded
                ) {
                    RowListItem(
                        onClick = {
                            isExpanded = !isExpanded
                        },
                        animatedVisibilityScope = this, //scope from animatedvisibility
                        modifier = Modifier
                            //sharedBounds to animate the outerbounds
                            .sharedBounds(
                                //notice the key which has to be unique
                                sharedContentState = rememberSharedContentState("item-layout"),
                                animatedVisibilityScope = this
                            )
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                //animatedVisibility requires a column scope
                this@Column.AnimatedVisibility(
                    visible = isExpanded
                ) {
                    ColumnListItem(
                        onClick = {
                            isExpanded = !isExpanded
                        },
                        animatedVisibilityScope = this,
                        modifier = Modifier
                            .sharedBounds(
                                sharedContentState = rememberSharedContentState("item-layout"),
                                animatedVisibilityScope = this
                            )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
//make an extension of SharedTransitionScope to access sharedElement modifier extension
fun SharedTransitionScope.RowListItem(
    onClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .padding(16.dp)
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.kermit),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                // do this for all the elements you want to share
                .sharedElement(
                    state = rememberSharedContentState("image"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "List item",
                fontSize = 20.sp,
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState("title"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            )
            Text(
                text = "This is the list item description",
                fontSize = 14.sp,
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState("description"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ColumnListItem(
    onClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.kermit),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(250.dp)
                .sharedElement(
                    state = rememberSharedContentState("image"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
        )
        Text(
            text = "List item",
            fontSize = 20.sp,
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState("title"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
        )
        Text(
            text = "This is the list item description",
            fontSize = 14.sp,
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState("description"),
                    animatedVisibilityScope = animatedVisibilityScope
                )
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun SharedTransitionAnimationPreview() {
    JetpackComposeMasterclassTheme {
        SharedTransitionAnimation()
    }
}