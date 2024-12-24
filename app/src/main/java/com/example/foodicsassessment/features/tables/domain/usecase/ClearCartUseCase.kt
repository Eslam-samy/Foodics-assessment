package com.example.foodicsassessment.features.tables.domain.usecase

import com.example.foodicsassessment.core.domain.util.NetworkError
import com.example.foodicsassessment.core.domain.util.Result
import com.example.foodicsassessment.features.tables.domain.model.products.Product
import com.example.foodicsassessment.features.tables.domain.repository.MenuRepository

class ClearCartUseCase(
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke(): Result<Boolean, NetworkError> {
        return menuRepository.clearCart()
    }
}