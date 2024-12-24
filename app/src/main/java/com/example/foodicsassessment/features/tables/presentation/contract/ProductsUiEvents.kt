package com.example.foodicsassessment.features.tables.presentation.contract

import com.example.foodicsassessment.core.UiEvent
import com.example.foodicsassessment.features.tables.data.model.cart.CartDto
import com.example.foodicsassessment.features.tables.domain.model.products.Product

sealed class ProductsUiEvents : UiEvent() {
    data class AddToCart(val product: Product) : ProductsUiEvents()
    data class SetInitialData(val categoryId: Int?, val key: String?) : ProductsUiEvents()
}
