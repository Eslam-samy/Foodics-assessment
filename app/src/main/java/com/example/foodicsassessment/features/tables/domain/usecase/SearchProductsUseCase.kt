package com.example.foodicsassessment.features.tables.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.foodicsassessment.features.tables.data.paging.ProductsPagingSource
import com.example.foodicsassessment.features.tables.domain.model.products.Product
import com.example.foodicsassessment.features.tables.domain.repository.MenuRepository

import kotlinx.coroutines.flow.Flow

class SearchProductsUseCase(private val repository: MenuRepository) {

    operator fun invoke(
        word: String? = null,
        categoryId: Int? = null,
    ): Flow<PagingData<Product>> {
        return Pager(config = PagingConfig(pageSize = 10)) {
            ProductsPagingSource(repository, word, categoryId)
        }.flow
    }
}