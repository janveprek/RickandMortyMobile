package com.veprek.honza.rickandmorty.design.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFFF4F4F9)
val Gray = Color(0xFFF0F0F0)
val Blue = Color(0xFF0000FF)

internal val lightColors =
    lightColors(
        primary = Primary,
        primaryVariant = Blue,
        secondary = Color.White,
        onPrimary = Color.Black,
    )

internal val darkColors =
    lightColors(
        primary = Primary,
        primaryVariant = Blue,
        secondary = Color.White,
        onPrimary = Color.Black,
    )
