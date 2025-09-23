package com.plcoding.jetpackcomposemasterclass.state_management.assignments

data class Todo(
    val title: String = "Bring out the trash",
    val description: String = "Better do this before wife comes home",
    val isChecked: Boolean = false
)
