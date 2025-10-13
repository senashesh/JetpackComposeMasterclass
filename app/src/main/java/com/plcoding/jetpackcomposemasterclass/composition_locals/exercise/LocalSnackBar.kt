package com.plcoding.jetpackcomposemasterclass.composition_locals.exercise

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

/* MainActivity Code...
     setContent {
            JetpackComposeMasterclassTheme {
                val state = LocalSnackBar.current
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = state) }) { innerPadding ->
                    LocalSnackBarDemo(modifier = Modifier.padding(innerPadding))

                }
            }
        }
 */

val LocalSnackBar = compositionLocalOf { SnackbarHostState() }

@Composable
fun LocalSnackBarDemo(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val state = LocalSnackBar.current
    Box(modifier = modifier.fillMaxSize()){
        Button(
            onClick = {
                scope.launch {
                    state.showSnackbar("Hello world!")
                }
            },
            Modifier.align(Alignment.Center)
        ) {
            Text("Click me")
        }
    }

}