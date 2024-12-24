package com.example.foodicsassessment.features.tables.domain.usecase

import com.example.foodicsassessment.core.domain.util.NetworkError
import com.example.foodicsassessment.core.domain.util.Result
import com.example.foodicsassessment.features.tables.domain.model.products.Product
import com.example.foodicsassessment.features.tables.domain.repository.MenuRepository

class AddProductToCartUseCase (
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke(product: Product, amount: Int): Result<Boolean, NetworkError> {
        return menuRepository.addToCart(product, amount)
    }
}