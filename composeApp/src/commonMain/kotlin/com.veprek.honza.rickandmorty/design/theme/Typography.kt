package com.veprek.honza.rickandmorty.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val typography
    get() =
        ThemeTypography(
            titleMedium =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                ),
            bodyMedium =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    lineHeight = 22.sp,
                ),
            labelMedium =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Medium,
                    fontSize = 17.sp,
                    lineHeight = 22.sp,
                ),
            labelLarge =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                ),
            labelSmall =
                TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 14.4.sp,
                ),
        )

@Immutable
data class ThemeTypography(
    val titleMedium: TextStyle,
    val bodyMedium: TextStyle,
    val labelMedium: TextStyle,
    val labelLarge: TextStyle,
    val labelSmall: TextStyle,
)

internal val LocalThemeTypography = staticCompositionLocalOf { typography }
