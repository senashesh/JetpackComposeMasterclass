package com.plcoding.jetpackcomposemasterclass.composition_locals

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.jetpackcomposemasterclass.ui.theme.JetpackComposeMasterclassTheme

/* Main Activity Code
     JetpackComposeMasterclassTheme {
                //Overriding our custom shape called LocalShape below
                CompositionLocalProvider(LocalShape provides TriangleShape) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                    ) { innerPadding ->
                        MyShapedButton()
                    }
                }
            }
 */


//for non static CompostionLocal use compositionLocalOf to create
val LocalShape = compositionLocalOf {
    RectangleShape
}

@Composable
fun MyShapedButton(
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {},
        shape = LocalShape.current
    ) {
        Text("Hello world")
    }
}

@Preview(showBackground = true)
@Composable
private fun MyShapeButtonPreview() {
    JetpackComposeMasterclassTheme {
        MyShapedButton()
    }
}