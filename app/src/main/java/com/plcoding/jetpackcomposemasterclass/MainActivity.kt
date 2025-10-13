package com.plcoding.jetpackcomposemasterclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.jetpackcomposemasterclass.side_effects.exercises.SideEffectFreeList
import com.plcoding.jetpackcomposemasterclass.side_effects.exercises.SideEffectFreeViewModel
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeMasterclassTheme {

                val viewModel = viewModel<SideEffectFreeViewModel>()
                val list = viewModel.list
                val lazyListState = viewModel.lazyListState
                val canScrollForward by viewModel.canScrollForward.collectAsStateWithLifecycle()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = viewModel.snackbarHostState) }) { innerPadding ->

                    SideEffectFreeList(
                        list = list,
                        listState = lazyListState,
                        canScrollForward = canScrollForward,
                        showSnackBar = { scope ->
                            viewModel.showSnackBar(scope)
                        },
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





