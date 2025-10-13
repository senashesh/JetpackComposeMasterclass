package com.plcoding.jetpackcomposemasterclass.side_effects

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme
import kotlinx.coroutines.launch

@Composable
fun LaunchedEffectDemo(modifier: Modifier = Modifier) {
    var counter by remember {
        mutableIntStateOf(0)
    }
    /*
        - If the compose leaves the compostion or its not actively a part of our active UI tree
        then it cancels this coroutine
        val scope = rememberCoroutineScope()
        - Also don't ever call scope.launch{ } here because that is a side effect again
        because its a non -compose function
     */

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    // add this in manifiest: <uses-permission android:name="android.permission.RECORD_AUDIO" />
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->

    }
    /*
        - setting the key to Unit or true will make sure state never changes so it will only fire one time
        - useful for codes that only needs to be called one time when user first enters the screen
        - Caveat: Don't over use Launched effect. If a state changes can be observed in viewmodel no need to
        observe the changes in launched effect just to send them back to viewmodel which is pointless.
     */

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.RECORD_AUDIO)
    }

    //A composable function that takes in key as a state, keys can be multiple
    LaunchedEffect(counter) {
        if(counter % 2 == 0) {
            //this lambda already contains a coroutine scope so showSnackbar can be called
            //also, if the state is changed then it will cancel this coroutine automatically
            snackbarHostState.showSnackbar(
                "The count is even!"
            )
        }
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->
        Button(
            onClick = {
                counter++
//                if(counter % 2 == 0) {
//                    scope.launch {
//                        snackbarHostState.showSnackbar(
//                            "The number is even!"
//                        )
//                    }
//                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .wrapContentSize()
        ) {
            Text("Counter: $counter")
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun LaunchedEffectDemoPreview() {
    JetpackComposeMasterclassTheme {
        LaunchedEffectDemo()
    }
}