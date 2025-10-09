

package com.plcoding.jetpackcomposemasterclass.state_management.assignments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme


@Composable
fun ToDoListScreen(modifier: Modifier = Modifier) {
    val viewModel = viewModel<ToDoViewModel>()
    val toDoState by viewModel.toDoState.collectAsStateWithLifecycle()

    ToDoRootList(
        modifier = modifier, toDoList = toDoState, onAction = {
            viewModel.onAction(it)
        })
}

@Composable
fun ToDoRootList(
    toDoList: List<Todo>,
    modifier: Modifier = Modifier,
    onAction: (ToDoAction) -> Unit = {},
) {

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ToDoList(
            modifier = Modifier.weight(1f), toDoList = toDoList, onAction = onAction
        )
        BottomScreen(
            onAddItem = {
                onAction(ToDoAction.OnAddItem(it))
            })
    }
}

@Composable
fun ToDoList(
    modifier: Modifier = Modifier,
    toDoList: List<Todo>,
    onAction: (ToDoAction) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(toDoList) { item ->
            ToDoItem(toDo = item, onDelete = {
                onAction(
                    ToDoAction.OnDeleteItem(item)
                )
            }, onCheckedChange = {
                onAction(
                    ToDoAction.OnCheckedChanged(item)
                    )
            })
        }
    }
}

@Composable
fun ToDoItem(
    toDo: Todo,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit = {},
    onCheckedChange: () -> Unit = {},

    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = toDo.title,
                fontWeight = FontWeight.Bold,
                textDecoration = if (toDo.isChecked) {
                    TextDecoration.LineThrough
                } else null
            )
            Text(
                text = toDo.description, textDecoration = if (toDo.isChecked) {
                    TextDecoration.LineThrough
                } else null
            )
        }
        Checkbox(
            checked = toDo.isChecked,
            onCheckedChange = {
                onCheckedChange()
            },
        )
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                onDelete()
            })
    }
}

@Composable
fun BottomScreen(
    modifier: Modifier = Modifier,
    onAddItem: (Todo) -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var filledTitle by remember { mutableStateOf("") }
        var filledDescription by remember { mutableStateOf("") }
        Column(
            modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(modifier = Modifier.fillMaxWidth(), value = filledTitle, onValueChange = {
                filledTitle = it
            }, placeholder = {
                Text(text = "Title")
            })
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = filledDescription,
                onValueChange = {
                    filledDescription = it
                },
                placeholder = {
                    Text(text = "Description")
                },
                maxLines = 3,
            )
        }
        Button(
            onClick = {
                if(filledTitle.isNotEmpty() && filledDescription.isNotEmpty()){
                    val item = Todo(
                        title = filledTitle,
                        description = filledDescription,
                        isChecked = false,
                    )
                    onAddItem(item)
                }
            }) {
            Text("Add")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ToDoRootListPreview() {
    val toDoList = Todo.newList()
    JetpackComposeMasterclassTheme {
        ToDoRootList(toDoList = toDoList)
    }
}

@Preview(showBackground = true)
@Composable
private fun ToDoListPreview() {
    val toDoList = Todo.newList()
    JetpackComposeMasterclassTheme {
        ToDoList(toDoList = toDoList)
    }

}

@Preview(showBackground = true)
@Composable
private fun ToDoItemPreview() {
    JetpackComposeMasterclassTheme {
        ToDoItem(
            toDo = Todo(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomScreenPreview() {
    JetpackComposeMasterclassTheme {
        BottomScreen()
    }
}
