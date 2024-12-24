package com.example.foodicsassessment.features.tables.domain.model.products


import com.example.foodicsassessment.features.tables.data.model.products.ProductDto
import com.example.foodicsassessment.features.tables.domain.model.common.Category

data class Product(
    val category: Category? = null,
    val description: String? = null,
    val id: Int? = null,
    val image: String? = null,
    val name: String? = null,
    val price: Double? = null
){
    fun toProductDto(): ProductDto {
        return ProductDto(
            category?.toCategoryDto(), description, id!!, image, name,price
        )
    }
}