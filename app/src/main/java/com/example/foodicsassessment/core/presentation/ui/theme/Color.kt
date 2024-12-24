package com.example.foodicsassessment.core.presentation.ui.theme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColor(
    val light: Color,
    val lightHover: Color,
    val lightActive: Color,
    val normal: Color,
    val normalHover: Color,
    val normalActive: Color,
    val dark: Color,
    val darkHover: Color,
    val darkActive: Color,
    val darker: Color,
    val backgroundColor: Color,
    val errorColor: Color,
    val yellowColor:Color,
    val backgroundColor2 :Color,
    val textColor :Color,
)

val lightColorScheme = AppColor(
    light = Color(0xFFEAF2F9),
    lightHover = Color(0xFFE0EBF6),
    lightActive = Color(0xFFBED6EC),
    normal = Color(0xFF2D7BC3),
    normalHover = Color(0xFF296FB0),
    normalActive = Color(0xFF24629C),
    dark = Color(0xFF225C92),
    darkHover = Color(0xFF1B4A75),
    darkActive = Color(0xFF143758),
    darker = Color(0xFF102B44),
    backgroundColor = Color.White,
    errorColor = Color(0xffFF1100),
    yellowColor = Color(0xffFFC000),
    backgroundColor2 = Color(0xffF7F7F7),
    textColor = Color.Black,

)

val darkColorScheme = AppColor(
    light =Color(0xFFEAF2F9),
    lightHover = Color(0xFFE0EBF6),
    lightActive = Color(0xFFBED6EC),
    normal = Color(0xFF2D7BC3),
    normalHover = Color(0xFF296FB0),
    normalActive = Color(0xFF24629C),
    dark = Color(0xFF225C92),
    darkHover = Color(0xFF1B4A75),
    darkActive = Color(0xFF143758),
    darker = Color(0xFF102B44),
    backgroundColor =  Color.White,
    errorColor = Color(0xffFF1100),
    yellowColor = Color(0xffFFC000),
    backgroundColor2 = Color(0xffF7F7F7),
    textColor = Color.Black



)

val LocalColors = compositionLocalOf { lightColorScheme }

val MaterialTheme.colors: AppColor
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

