package com.plcoding.jetpackcomposemasterclass.state_management.assignments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme


@Composable
fun ToDoScreen(modifier: Modifier = Modifier) {
    var toDo by remember { mutableStateOf(Todo()) }
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Column(modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = toDo.title,
                fontWeight = FontWeight.Bold,
                textDecoration = if(toDo.isChecked){
                    TextDecoration.LineThrough
                }
                else null
            )
            Text(
                text = toDo.description,
                textDecoration = if(toDo.isChecked){
                    TextDecoration.LineThrough
                }
                else null
            )
        }
        Checkbox(
            checked = toDo.isChecked,
            onCheckedChange = {
               toDo = toDo.copy(
                   isChecked = !toDo.isChecked
               )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ToScreenCheckedPreview() {
    JetpackComposeMasterclassTheme {
        ToDoScreen()
    }
}