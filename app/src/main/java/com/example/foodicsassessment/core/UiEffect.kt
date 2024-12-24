package com.example.foodicsassessment.core
abstract class UiEffect

sealed class CommonUiEffect : UiEffect() {
    data class ShowDialog(val uiText: UiText, val success: Boolean = false) : CommonUiEffect()

    data class ShowToast(val uiText: UiText) : CommonUiEffect()

    data class Navigate(
        val route: Any,
        val popUpTo: Any? = null,
        val inclusive: Boolean = false,
        val singleTop: Boolean = false
    ) : CommonUiEffect()

    data object NavigateUp : CommonUiEffect()

    data class ShowUnauthenticatedState(val uiText: UiText) : CommonUiEffect()
}