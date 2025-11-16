package com.plcoding.jetpackcomposemasterclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.jetpackcomposemasterclass.animations.exercises.RotatingTriangles
import com.plcoding.jetpackcomposemasterclass.animations.exercises.ValueScreen
import com.plcoding.jetpackcomposemasterclass.composition_locals.exercise.LocalSnackBar
import com.plcoding.jetpackcomposemasterclass.composition_locals.exercise.LocalSnackBarDemo
import com.plcoding.jetpackcomposemasterclass.performance.LazyListPerformance
import com.plcoding.jetpackcomposemasterclass.performance.homework.ListItemScreenRoot
import com.plcoding.jetpackcomposemasterclass.side_effects.exercises.SideEffectFreeList
import com.plcoding.jetpackcomposemasterclass.side_effects.exercises.SideEffectFreeViewModel
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeMasterclassTheme {
                val state = LocalSnackBar.current
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()

                ) { innerPadding ->
                    ListItemScreenRoot(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

private fun randomList(): List<String> {
    return List(20) { i ->
        "Item ${i + 1}"
    }
}





