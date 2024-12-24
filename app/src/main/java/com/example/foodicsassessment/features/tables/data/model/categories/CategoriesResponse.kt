package com.example.foodicsassessment.features.tables.data.model.categories


import com.example.foodicsassessment.features.tables.data.model.common.CategoryDto
import com.example.foodicsassessment.features.tables.domain.model.categories.Categories
import com.example.foodicsassessment.features.tables.domain.model.products.Products
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponse(
    @SerialName("data")
    val `data`: List<CategoryDto>? = null
) {
    fun toCategories(): Categories {
        return Categories(
            data = data?.map {
                it.toCategory()
            }
        )
    }
}