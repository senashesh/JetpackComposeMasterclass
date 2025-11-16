package com.plcoding.jetpackcomposemasterclass.internals

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

//@Immutable - promises the data class will not go any changes
//@Stable - promises the any change on the list means we replace it by new list
data class Person(
    val name: String,
    val age: Int,
    val list: List<Int>
)

val list1 = listOf(1, 2, 3)
val list2 = listOf(1, 2, 3, 4)
val result = list1 == list2

@Composable
fun StabilityDemo(
    person: Person,
    modifier: Modifier = Modifier
) {
    Text(
        text = "${person.name} is ${person.age} years old.",
        modifier = modifier
    )
}