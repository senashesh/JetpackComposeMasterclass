package com.plcoding.jetpackcomposemasterclass.internals

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/*
    https://pl-coding.mymemberspot.de/library/aUvl7Unv6eye8zuJA75P/94QLUa6k3MhnsMHQhxBE/Y9K2lPz0fIdSh7A2ENLt/details

    Tools > Kotlin > Show Kotlin ByteCode > Decompile
 */
@Composable
fun ComposeCompilerDemo(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier
    )
}