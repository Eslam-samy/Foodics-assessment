package com.example.foodicsassessment.features.tables.domain.usecase


import com.example.foodicsassessment.core.domain.util.NetworkError
import com.example.foodicsassessment.core.domain.util.Result
import com.example.foodicsassessment.features.tables.domain.model.categories.Categories
import com.example.foodicsassessment.features.tables.domain.model.common.Category
import com.example.foodicsassessment.features.tables.domain.repository.MenuRepository

class GetCategoriesUseCase(private val repository: MenuRepository) {

    suspend operator fun invoke(): Result<List<Category>, NetworkError> {
        return repository.getCategories()
    }
}