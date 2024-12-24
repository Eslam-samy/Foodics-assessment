package com.example.foodicsassessment.features.tables.data.model.cart

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodicsassessment.features.tables.data.model.products.ProductDto
import com.example.foodicsassessment.features.tables.domain.model.products.Product


@Entity(tableName = "cart")
data class CartDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val product: ProductDto,
    val amount: Int = 0
)
