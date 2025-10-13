package com.plcoding.jetpackcomposemasterclass.composition_locals

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

@Composable
fun CompositionLocalDemo(modifier: Modifier = Modifier) {
    val contentColor = LocalContentColor.current
    val textStyle = LocalTextStyle.current
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Red
        )
    ) {
        CompositionLocalProvider( //Override the content color
            LocalContentColor provides Color.Green
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
            )
            Text("Hello world")
        }
    }
}

@Composable
fun MyCustomTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides LocalTextStyle.current.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 28.sp
            ) //This will be now the default value of the composable title
        ) {
            title()
        }
    }
}

@Preview
@Composable
private fun CompositionLocalDemoPreview() {
    JetpackComposeMasterclassTheme {
        MyCustomTopAppBar(
            title = {
                Text(
                    text = "Hello world", // you can override the default styles here
                )
            }
        )
    }
}