package com.plcoding.jetpackcomposemasterclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.jetpackcomposemasterclass.measurements.LazyMindMap
import com.plcoding.jetpackcomposemasterclass.measurements.MindMapItem
import com.plcoding.jetpackcomposemasterclass.measurements.exercises.ExtMindMapItem
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.DpOffset
import com.plcoding.jetpackcomposemasterclass.measurements.exercises.ConstraintsInDP
import com.plcoding.jetpackcomposemasterclass.measurements.exercises.LazyMindMapExtended

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeMasterclassTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    val mindMapItems = remember {
                        listOf(

                            ExtMindMapItem(
                                content = {IncDecCompose()},
                                itemConstraints = ConstraintsInDP(maxWidth = 200.dp, maxHeight = 200.dp),
                                offset = DpOffset(x = (-400).dp, y = (-200).dp)
                            ),

                            ExtMindMapItem(
                                content = { MindMapToDo() },
                                itemConstraints = ConstraintsInDP(
                                    maxWidth = 300.dp,
                                    maxHeight = 100.dp
                                ),
                                offset = DpOffset(0.dp, 0.dp)
                            ),
                            ExtMindMapItem(
                                content = {IncDecCompose()},
                                itemConstraints = ConstraintsInDP(maxWidth = 200.dp, maxHeight = 200.dp),
                                offset = DpOffset(x = 400.dp, y = 200.dp)
                            ),



                        )
                    }

                    var mindMapOffset by remember {
                        mutableStateOf(IntOffset.Zero)
                    }
                    LazyMindMapExtended(
                        items = mindMapItems, mindMapOffset = mindMapOffset, onDrag = { delta ->
                            mindMapOffset += delta
                        }, modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MindMapToDo(title: String = "MindMap ToDo",
                description:String = "Description",
                modifier: Modifier = Modifier) {
    var isChecked by remember { mutableStateOf(false) }
    Row(modifier = modifier
        .fillMaxWidth()
        .border(width = 1.dp,color = Color.Black)
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier =Modifier
            .weight(1f)) {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = description)

        }
        Checkbox(checked = isChecked,
            onCheckedChange = {isChecked = !isChecked})
    }
}

@Composable
fun IncDecCompose(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Column(modifier = modifier
        .fillMaxWidth()
        .border(width = 1.dp,color = Color.Black)
        .padding(16.dp),
       horizontalAlignment = Alignment.CenterHorizontally ) {
        Text(text = "$count", fontWeight = FontWeight.Bold, fontSize = 34.sp)
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = {count++}) {
                Text(text = "Inc")
            }
            Button(onClick = {count--}) {
                Text(text = "Dec")
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun MindMapToDoPreview() {
    JetpackComposeMasterclassTheme {
        Box(modifier = Modifier.padding(16.dp)){
            MindMapToDo()
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun IncDecComposePreview() {
    JetpackComposeMasterclassTheme {
        Box(modifier = Modifier.padding(16.dp)){
            IncDecCompose()
        }
    }
}

/* HotelBookingScreen(
                         modifier = Modifier
                             .padding(innerPadding)
                     )*//*NumberGuessScreenRoot(
                        modifier = Modifier
                        .padding(innerPadding)
                    )*//*ToDoListScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                    )*/


