package com.plcoding.jetpackcomposemasterclass.state_management.assignments

sealed interface ToDoAction {
    data class OnDeleteItem(val item: Todo): ToDoAction
    data class OnCheckedChanged(val item: Todo): ToDoAction
    data class OnAddItem(val item: Todo): ToDoAction
}