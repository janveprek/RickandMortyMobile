package com.veprek.honza.rickandmorty.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun RickAndMortyTheme(
    isInDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (isInDarkMode) darkColors else lightColors

    MaterialTheme(
        colors = colors,
        content = content,
    )
}

object RickAndMortyTheme {
    val typography: ThemeTypography
        @Composable
        get() = LocalThemeTypography.current
}
