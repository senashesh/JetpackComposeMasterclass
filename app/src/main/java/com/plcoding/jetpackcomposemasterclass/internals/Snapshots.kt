package com.plcoding.jetpackcomposemasterclass.internals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun snapshots() {
    var counter by mutableIntStateOf(0)

    val snapshot = Snapshot.takeMutableSnapshot()
    val observer = Snapshot.registerApplyObserver { states, snapshot ->
        for(change in states) {
            when(change) {
                is MutableState<*> -> {
                    println("Changed: ${change}")
                }
            }
        }
    }

    CoroutineScope(Dispatchers.Main).launch {
        delay(3000L)
        counter++
        delay(1000)
        observer.dispose()
    }

//    snapshot.enter {
//        counter++
//        println("The counter value is $counter")
//    }

    println("The counter value is $counter")

    snapshot.dispose()
}