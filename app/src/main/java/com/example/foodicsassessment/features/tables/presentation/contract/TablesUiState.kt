package com.example.foodicsassessment.features.tables.presentation.contract

import com.example.foodicsassessment.core.UiState
import com.example.foodicsassessment.features.tables.data.model.cart.CartDto
import com.example.foodicsassessment.features.tables.domain.model.common.Category

data class TablesUiState(
    val loading: Boolean = true,
    val categories: List<Category> = emptyList(),
    val selectedTabIndex: Int = 0,
    val userName: String = "Eslam Ssamy",
    val userId: String = "162845",
    val tablesCount: Int = 10,
    val peopleCount: Int = 2,
    val searchKey: String ="",
    val cartItems: List<CartDto> = emptyList(),  // Add cart items
    val cartCount: Int = 0  // Add cart count (optional)
) : UiState()
