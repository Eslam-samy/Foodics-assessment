package com.example.foodicsassessment.features.tables.domain.repository

import com.example.foodicsassessment.core.domain.util.NetworkError
import com.example.foodicsassessment.core.domain.util.Result
import com.example.foodicsassessment.features.tables.data.model.cart.CartDto
import com.example.foodicsassessment.features.tables.domain.model.categories.Categories
import com.example.foodicsassessment.features.tables.domain.model.common.Category
import com.example.foodicsassessment.features.tables.domain.model.products.Product
import kotlinx.coroutines.flow.Flow

interface MenuRepository {

    suspend fun getProducts(
        page: Int? = null,
        categoryId: Int? = null,
        query: String? = null
    ): Result<List<Product>, NetworkError>

    suspend fun getCategories(): Result<List<Category>, NetworkError>


    suspend fun addToCart(
        product: Product,
        amount: Int
    ): Result<Boolean, NetworkError>

    fun getCart(): Flow<List<CartDto>>

    suspend fun clearCart(
    ): Result<Boolean, NetworkError>
}