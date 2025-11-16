package com.plcoding.jetpackcomposemasterclass.performance

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.movableContentWithReceiverOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.R

data class Profile(
    val username: String,
    @DrawableRes val pictureResId: Int
)

@Composable
fun MovableContent(modifier: Modifier = Modifier) {
    var isCondensedView by remember {
        mutableStateOf(true)
    }
    var profile by remember {
        mutableStateOf(
            Profile(
                username = "John Doe",
                pictureResId = R.drawable.kermit
            )
        )
    }
    /*
        when you have parts of UI where the layout may have to move across
        or out of the layout it originally was in, use movableContentOf to avoid
        unnecessary recomposition.
     */
    val movableProfileImage = remember {
        movableContentOf {
            ProfileImage(profile.pictureResId)
        }
    }
    val movableUsername = remember {
        movableContentOf {
            Text(profile.username)
        }
    }
    Column(modifier) {
        if(isCondensedView) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                movableProfileImage()
                Spacer(modifier = Modifier.width(16.dp))
                movableUsername()
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                movableProfileImage()
                Spacer(modifier = Modifier.height(16.dp))
                movableUsername()
            }
        }
        Button(onClick = {
            isCondensedView = !isCondensedView
        }) {
            Text("Toggle")
        }
    }
}

@Composable
fun ProfileImage(
    pictureResId: Int,
    modifier: Modifier = Modifier
) {
    //gets called in every composition and recomposition
    SideEffect {
        println("Function was called!")
    }
    Image(
        painter = painterResource(pictureResId),
        contentDescription = "profile pic",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(75.dp)
            .clip(CircleShape)
    )
}