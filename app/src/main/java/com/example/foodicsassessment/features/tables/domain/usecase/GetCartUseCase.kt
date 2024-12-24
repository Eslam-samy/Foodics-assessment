package com.example.foodicsassessment.features.tables.domain.usecase

import com.example.foodicsassessment.features.tables.data.model.cart.CartDto
import com.example.foodicsassessment.features.tables.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow

// GetCartUseCase.kt
class GetCartUseCase (
    private val menuRepository: MenuRepository
) {
    fun execute(): Flow<List<CartDto>> {
        return menuRepository.getCart()
    }
}
