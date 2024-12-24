package com.example.foodicsassessment.features.tables.data.repository

import com.example.foodicsassessment.BuildConfig
import com.example.foodicsassessment.core.data.networking.SafeCall
import com.example.foodicsassessment.core.data.networking.constructUrl
import com.example.foodicsassessment.core.domain.util.NetworkError
import com.example.foodicsassessment.core.domain.util.Result
import com.example.foodicsassessment.features.tables.data.local.CartDao
import com.example.foodicsassessment.features.tables.data.local.CategoriesDao
import com.example.foodicsassessment.features.tables.data.local.ProductsDao
import com.example.foodicsassessment.features.tables.data.model.cart.CartDto
import com.example.foodicsassessment.features.tables.data.model.categories.CategoriesResponse
import com.example.foodicsassessment.features.tables.data.model.products.ProductsResponse
import com.example.foodicsassessment.features.tables.domain.model.common.Category
import com.example.foodicsassessment.features.tables.domain.model.products.Product
import com.example.foodicsassessment.features.tables.domain.repository.MenuRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

class MenuRepositoryImpl(
    private val httpClient: HttpClient,
    private val categoryDto: CategoriesDao,
    private val cartDao: CartDao,
    private val productsDto: ProductsDao
) : MenuRepository {
    override suspend fun getProducts(
        page: Int?,
        categoryId: Int?,
        query: String?
    ): Result<List<Product>, NetworkError> {
        val networkResult = SafeCall<ProductsResponse> {
            httpClient.get(
                urlString = constructUrl(
                    url = "products/.json"
                )
            ) {
                parameter("key", BuildConfig.KEY)
                parameter("page", page)
                parameter("category_id", categoryId)
                parameter("name", query)
            }
        }
        return when (networkResult) {
            is Result.Success -> {
                val products = networkResult.data.data ?: listOf()
                productsDto.insertProducts(products)

                Result.Success(products.map { it.toProduct() })
            }

            is Result.Error -> {
                // If network fails, fetch data from the database
                val cachedProducts = productsDto.getProducts(
                    name = query,
                    categoryId = categoryId,
                    pageSize = 10,
                    offset = (if (page != null) page - 1 else 0) * 10
                ).map { it.toProduct() }
                if (cachedProducts.isNotEmpty()) {
                    Result.Success(cachedProducts)
                } else {
                    Result.Success(listOf())
                }
            }
        }
    }

    override suspend fun getCategories(): Result<List<Category>, NetworkError> {
        val networkResult = SafeCall<CategoriesResponse> {
            httpClient.get(
                urlString = constructUrl(
                    url = "categories.json"
                )
            ) {
                parameter("key", BuildConfig.KEY)
            }
        }
        return when (networkResult) {
            is Result.Success -> {
                val categories = networkResult.data.data ?: listOf()

                // Save fetched data to database
                categoryDto.clearCategories()
                categoryDto.insertCategories(categories)

                Result.Success(categories.map { it.toCategory() })
            }

            is Result.Error -> {
                // If network fails, fetch data from the database
                val cachedCategories = categoryDto.getCategories(
                ).map { it.toCategory() }
                if (cachedCategories.isNotEmpty()) {
                    Result.Success(cachedCategories)
                } else {
                    Result.Error(networkResult.error) // Replace with your specific error
                }
            }
        }
    }

    override suspend fun addToCart(product: Product, amount: Int): Result<Boolean, NetworkError> {
        val existingCartItems =
            cartDao.getCart().first()  // Get the current cart items (suspend to collect flow)

        // Check if the product already exists in the cart
        val existingCartItem = existingCartItems.find { it.product.id == product.id }

        if (existingCartItem != null) {
            // If the product exists, update the amount
            val updatedCartItem = existingCartItem.copy(amount = existingCartItem.amount + amount)
            cartDao.insertProduct(updatedCartItem)  // Replace the old item with the updated one
        } else {
            // If the product doesn't exist in the cart, add a new one
            val newCartItem = CartDto(
                id = 0,  // Room will automatically generate the ID
                product = product.toProductDto(),
                amount = amount
            )
            cartDao.insertProduct(newCartItem)
        }

        return Result.Success(true)
    }

    override fun getCart(): Flow<List<CartDto>> {
        return cartDao.getCart()  // Return Flow for real-time updates
    }

    override suspend fun clearCart(): Result<Boolean, NetworkError> {
        cartDao.clearCart()
        return Result.Success(true)
    }


}