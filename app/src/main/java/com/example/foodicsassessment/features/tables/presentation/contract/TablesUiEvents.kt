package com.example.foodicsassessment.features.tables.presentation.contract

import com.example.foodicsassessment.core.UiEvent
import com.example.foodicsassessment.features.tables.domain.model.products.Product

sealed class TablesUiEvents : UiEvent() {
    data class OnError(val error: String) : TablesUiEvents()
    data class AddToCart(val product: Product) : TablesUiEvents()
    data class OnCategorySelected(val index: Int) : TablesUiEvents()
    data object OnCheckoutClicked : TablesUiEvents()
    data class OnSearchKeyChanged(val key: String) : TablesUiEvents()
}
