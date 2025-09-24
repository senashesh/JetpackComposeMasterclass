package com.plcoding.jetpackcomposemasterclass.state_management.assignments

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ToDoViewModel: ViewModel() {
    private val _toDoState = MutableStateFlow<List<Todo>>(Todo.newList())
    val toDoState = _toDoState.asStateFlow()

    fun onAction(action: ToDoAction){
        when(action){
            is ToDoAction.OnAddItem, -> {
                _toDoState.update {items ->
                    items + action.item
                }
            }
            is ToDoAction.OnCheckedChanged -> {
                _toDoState.update { items ->
                    items.map {  item ->
                        if(item == action.item){
                            item.copy(
                                isChecked = !item.isChecked
                            )
                        }
                        else
                            item
                    }
                }
            }
            is ToDoAction.OnDeleteItem -> {
                _toDoState.update { items ->
                    items - action.item
                }
            }
        }
    }

}