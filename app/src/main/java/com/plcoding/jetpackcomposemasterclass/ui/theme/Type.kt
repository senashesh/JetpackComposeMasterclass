package com.plcoding.jetpackcomposemasterclass.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

/*
    To use custom fonts download the font and put in res/fonts directory
    Define custom fonts here:
    val Jaro = FontFamily(
    Font(
        resId = R.font.jaro_regular,
        weight = FontWeight.Normal,
    ),
    Font(
        resId = R.font.jaro_60pt_regular,
        weight = FontWeight.Bold,
    ),
)
 */

/*
    https://m3.material.io/styles/typography/type-scale-tokens
    Typography has following display, headline, title, label body each come with three different sizes
    altogether 15 combinations which all have baseline and emphasized variations

    The emphasized styles were added in the expressive update. They have a higher weight and other minor
     adjustments compared to the baseline styles, and are best applied to bold, selection, and other
     areas of emphasis. Baseline and emphasized styles are meant to be used together.
 */
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default, //Jaro
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    ,
    displayLarge = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Black,
        fontStyle = FontStyle.Italic,
        textDecoration = TextDecoration.Underline,
        //fontFamily = Jaro
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)