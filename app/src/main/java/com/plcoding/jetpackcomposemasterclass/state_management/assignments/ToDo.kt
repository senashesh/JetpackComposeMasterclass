package com.plcoding.jetpackcomposemasterclass.state_management.assignments


import kotlin.random.Random

data class Todo(
    val title: String = "Bring out the trash",
    val description: String = "Better do this before wife comes home",
    val isChecked: Boolean = false
){
    companion object {
        fun newList(): List<Todo> {
            val toDoList = mutableListOf<Todo>()
            repeat(15) { count ->
                toDoList.add(
                    Todo(
                        title = "ToDo item $count",
                        description = "ToDo description $count",
                        isChecked = Random.nextBoolean()
                    )
                )
            }
            return toDoList
        }
    }
}
