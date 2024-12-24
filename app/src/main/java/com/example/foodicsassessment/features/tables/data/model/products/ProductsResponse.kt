package com.example.foodicsassessment.features.tables.data.model.products


import com.example.foodicsassessment.features.tables.domain.model.common.Category
import com.example.foodicsassessment.features.tables.domain.model.products.Products
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    @SerialName("data")
    val data: List<ProductDto>? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("total")
    val total: Int? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null
) {
    fun toProducts(): Products {
        return Products(
            data = data?.map { it.toProduct() }
        )
    }
}