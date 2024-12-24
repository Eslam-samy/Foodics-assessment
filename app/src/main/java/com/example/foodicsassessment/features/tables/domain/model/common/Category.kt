package com.example.foodicsassessment.features.tables.domain.model.common

import com.example.foodicsassessment.features.tables.data.model.common.CategoryDto


data class Category(
    val id: Int? = null,
    val name: String? = null
){
    fun toCategoryDto(): CategoryDto {
        return CategoryDto(
            id = id!!,
            name = name
        )
    }
}