package com.example.foodicsassessment.core.presentation.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val default: Dp = 0.dp,
    val xxSmall: Dp = 2.dp,
    val xSmall: Dp = 4.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val normal: Dp = 20.dp,
    val large: Dp = 24.dp,
    val xLarge: Dp = 32.dp,
    val xxLarge: Dp = 48.dp
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current