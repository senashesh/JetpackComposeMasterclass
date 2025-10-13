package com.plcoding.jetpackcomposemasterclass.side_effects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner

/*
    DisposableEffects are needed when we need to register to callback and unregister
    when we don't need them anymore.
 */

/*
    MainActivity Code:
    Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    var toggle by remember {
                        mutableStateOf(false)
                    }
                    if(!toggle) {
                        DisposableEffectDemo()
                    }
                    Button(
                        onClick = {
                            toggle = !toggle
                        },
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .wrapContentSize()
                    ) {
                        Text("Toggle")
                    }
                }

 */

@Composable
fun DisposableEffectDemo(modifier: Modifier = Modifier) {
    //LifecycleOwner is Fragment or Activity dependent on where the composable is placed.
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner.lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when(event) {
                Lifecycle.Event.ON_START -> {
                    println("OnStart was called!")
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        //if the composale leaves the composition then onDispose will be called
        onDispose {
            println("Observer was disposed!")
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}