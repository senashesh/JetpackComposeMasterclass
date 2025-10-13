package com.plcoding.jetpackcomposemasterclass.side_effects

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EffectHandlerViewModel: ViewModel() {

    /*
        Certain cases where some functionality are too deep in the UI hiearchy,
        it may be ok to use side effect to avoid extra code or trouble otherwise
        avoid side effects if possible.
     */

    private val _counter = MutableStateFlow(0)
    val counter = _counter.asStateFlow()

    val snackbarHostState = SnackbarHostState()

    val lazyListState = LazyListState()
    //here its fine to call snapshotFlow that converts compose state(lazyListState) to flow
    val canScrollToTop = snapshotFlow { lazyListState.firstVisibleItemIndex }
        .map { it >= 10 }
        .stateIn( //converts flow to stateflow
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )

    init {
        viewModelScope.launch {
            counter.collectLatest { counter ->
                if(counter % 2 == 0) {
                    snackbarHostState.showSnackbar("The counter is even.")
                }
            }
        }
    }

    fun scrollToTop(uiScope: CoroutineScope) {
        //you need the compose state to trigger animateScrollToItem
        //do not create a variable of uiScope in viewmodel because it will create memory leak
        //here its fine since its a function call parameter
        viewModelScope.launch {
            withContext(uiScope.coroutineContext) {
                lazyListState.animateScrollToItem(0)
            }
        }
    }

    fun increaseCounter() {
        _counter.value++
    }
}

@Composable
fun BetterSideEffectDemo(modifier: Modifier = Modifier) {
    val viewModel = viewModel<EffectHandlerViewModel>()
    val counter by viewModel.counter.collectAsStateWithLifecycle()
    val canScrollToTop by viewModel.canScrollToTop.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->

    }
    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.RECORD_AUDIO)
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = viewModel.snackbarHostState
            )
        },
        floatingActionButton = {
            if(canScrollToTop) {
                FloatingActionButton(
                    onClick = {
                        viewModel.scrollToTop(scope)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Scroll to op"
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            LazyColumn(
                state = viewModel.lazyListState,
                modifier = Modifier
                    .weight(1f)
            ) {
                items(100) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
            Button(
                onClick = {
                    viewModel.increaseCounter()
//                if(counter % 2 == 0) {
//                    scope.launch {
//                        snackbarHostState.showSnackbar(
//                            "The number is even!"
//                        )
//                    }
//                }
                },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Counter: $counter")
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun BetterSideEffectDemoPreview() {
    JetpackComposeMasterclassTheme {
        BetterSideEffectDemo()
    }
}