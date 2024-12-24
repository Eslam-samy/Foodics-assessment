package com.example.foodicsassessment.core.presentation.util
import androidx.navigation.NavHostController

fun NavHostController.navigateTo(
    route: Any,
    popUpRoute: Any? = null,
    isInclusive: Boolean = false,
    singleTop: Boolean = false
) {
    navigate(route) {
        popUpRoute?.let { popRoute ->
            popUpTo(popRoute) {
                inclusive = isInclusive
            }
        }
        launchSingleTop = singleTop
    }
}