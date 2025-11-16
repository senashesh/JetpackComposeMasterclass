package com.plcoding.jetpackcomposemasterclass.performance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Section(
    val id: Int,
    val header: String,
    val description: String
)

@Composable
fun KeysCustomLayout(modifier: Modifier = Modifier) {
    var sections by remember {
        mutableStateOf(
            (1..3).map {
                Section(
                    id = it,
                    header = "Section $it Header",
                    description = "Section $it description"
                )
            }
        )
    }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (section in sections) {
            /*
                for any type of composables that takes in list of items, where their
                order might change use key composable to avoid unnecessary recomposition.
             */
            key(section.id) {
                Section(section)
            }
        }
        Button(
            onClick = {
                sections = sections.shuffled()
            }
        ) {
            Text("Shuffle")
        }
    }
}

@Composable
fun Section(
    section: Section,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = section.header,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = section.description,
        )
    }
}