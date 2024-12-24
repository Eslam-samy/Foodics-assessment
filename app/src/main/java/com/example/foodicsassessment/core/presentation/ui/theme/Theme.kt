package com.example.foodicsassessment.core.presentation.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        darkColorScheme
    } else {
        lightColorScheme
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalColors provides colors
    ) {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme.copy(
                background = colors.backgroundColor,
            ),
            shapes = shapes,
            content = content,
            typography = getTypography()
        )
    }

}