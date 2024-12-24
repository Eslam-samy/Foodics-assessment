package com.example.foodicsassessment.features.tables.presentation.contract

import com.example.foodicsassessment.core.UiState
import com.example.foodicsassessment.features.tables.data.model.cart.CartDto

data class ProductsUiState(
    val searchKey: String? = null,
    val categoryId: Int? = null,
    val cartItems: List<CartDto> = emptyList(),  // Add cart items
    val cartCount: Int = 0  // Add cart count (optional)
) : UiState()
