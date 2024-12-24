package com.example.foodicsassessment.features.tables.data.model.products


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodicsassessment.features.tables.data.model.common.CategoryDto
import com.example.foodicsassessment.features.tables.domain.model.products.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "products")

data class ProductDto(
    @SerialName("category")
    val category: CategoryDto? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("id")
    @PrimaryKey
    val id: Int,
    @SerialName("image")
    val image: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("price")
    val price: Double? = null,
    @ColumnInfo(name = "category_id")  // Added column for category ID
    val categoryId: Int? = category?.id
) {
    fun toProduct(): Product {
        return Product(
            category?.toCategory(), description, id, image, name,price
        )
    }
}