package com.plcoding.jetpackcomposemasterclass.internals

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun GroupsDemo(modifier: Modifier = Modifier) {
    var toggle by remember {
        mutableStateOf(false)
    }

    if(toggle) {
        key("Hello") {
            Text("Hello world!")
        }
    } else {
        Text("Bye bye world!")
    }
}

/*
    https://pl-coding.mymemberspot.de/library/aUvl7Unv6eye8zuJA75P/94QLUa6k3MhnsMHQhxBE/tRff6WJ4ZzIPd0M3dn5A/details

    Tools > Kotlin > Show Kotlin ByteCode > Decompile

    Compose groups have ids to distinguish them
    and they are of three types: restartable, movable and replaceable


 */