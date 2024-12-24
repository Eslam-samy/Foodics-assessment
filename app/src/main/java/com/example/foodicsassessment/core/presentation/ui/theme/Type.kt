package com.example.foodicsassessment.core.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.foodicsassessment.R


@Composable
fun getTypography(): Typography {
    val bodyFontFamily = FontFamily(
        Font(R.font.gabarito_regular, FontWeight.Normal, FontStyle.Normal),
        Font(R.font.gabarito_medium, FontWeight.Medium, FontStyle.Normal),
        Font(R.font.gabarito_bold, FontWeight.Bold, FontStyle.Normal),
        Font(R.font.gabarito_black, FontWeight.Black, FontStyle.Normal),
        Font(R.font.gabarito_semibold, FontWeight.SemiBold, FontStyle.Normal),
        Font(R.font.gabarito_extrabold, FontWeight.ExtraBold, FontStyle.Normal),

        )
    val baseline = Typography()

    return Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = bodyFontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = bodyFontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = bodyFontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = bodyFontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = bodyFontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = bodyFontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = bodyFontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = bodyFontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = bodyFontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),


        )

}

// Add typography extensions
val Typography.h1: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.gabarito_regular, FontWeight.Normal),
            Font(R.font.gabarito_bold, FontWeight.Bold),
            Font(R.font.gabarito_medium, FontWeight.Medium),
        ),
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
    )
// Add typography extensions
val Typography.header: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.gabarito_regular, FontWeight.Normal),
            Font(R.font.gabarito_bold, FontWeight.Bold),
            Font(R.font.gabarito_medium, FontWeight.Medium),
        ),
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal,
    )

val Typography.text_button: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.gabarito_regular, FontWeight.Normal),
            Font(R.font.gabarito_bold, FontWeight.Bold),
            Font(R.font.gabarito_medium, FontWeight.Medium),
        ),
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
    )
val Typography.body_text: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.gabarito_regular, FontWeight.Normal),
            Font(R.font.gabarito_bold, FontWeight.Bold),
            Font(R.font.gabarito_medium, FontWeight.Medium),
        ),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    )
val Typography.mid_text: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.gabarito_regular, FontWeight.Normal),
            Font(R.font.gabarito_bold, FontWeight.Bold),
            Font(R.font.gabarito_medium, FontWeight.Medium),
        ),
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    )
val Typography.small_text: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.gabarito_regular, FontWeight.Normal),
            Font(R.font.gabarito_bold, FontWeight.Bold),
            Font(R.font.gabarito_medium, FontWeight.Medium),
        ),
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    )
val Typography.small_header: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.gabarito_regular, FontWeight.Normal),
            Font(R.font.gabarito_bold, FontWeight.Bold),
            Font(R.font.gabarito_medium, FontWeight.Medium),
        ),
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
    )
