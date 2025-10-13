package com.plcoding.jetpackcomposemasterclass.side_effects.exercises

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/* Main Activity Code
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
 */


class SideEffectFreeViewModel : ViewModel() {
    private val TAG = "SideEffectViewModel"
    val list = randomList()
    val snackbarHostState = SnackbarHostState()

    val lazyListState = LazyListState()
    val canScrollForward =
        snapshotFlow { lazyListState.canScrollForward }.distinctUntilChanged().onEach { value ->
                Log.d(TAG, "canScrollForward changed: $value")
            }.stateIn(
                viewModelScope, SharingStarted.WhileSubscribed(5000L), true
            )
    /*
        Alternative:
        snapshotFlow {
        val layoutInfo = state.layoutInfo
        layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
    }
     */

    fun showSnackBar(uiScope: CoroutineScope) {
        Log.d(TAG, "showSanckBar called")
        viewModelScope.launch {
            withContext(uiScope.coroutineContext){
                snackbarHostState.showSnackbar("Scrolled to the bottom!")
            }
        }
    }

    private fun randomList(): List<String> {
        return List(20) { i ->
            "Item ${i + 1}"
        }
    }
}


@Composable
fun SideEffectFreeList(
    list: List<String>,
    listState: LazyListState,
    canScrollForward: Boolean,
    showSnackBar: (uiScope: CoroutineScope) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()

    Log.d("TEST", canScrollForward.toString())
    if (!canScrollForward) {
        showSnackBar(scope)
    }

    LazyColumn(
        state = listState,
        modifier = modifier,
    ) {
        itemsIndexed(list) { index, item ->
            Text(
                text = item, fontSize = 24.sp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            if (index != list.size - 1) {
                HorizontalDivider()
            }
        }
    }
}


