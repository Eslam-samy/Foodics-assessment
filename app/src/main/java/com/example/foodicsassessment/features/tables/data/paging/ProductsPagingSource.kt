package com.example.foodicsassessment.features.tables.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.foodicsassessment.features.tables.domain.model.products.Product
import com.example.foodicsassessment.features.tables.domain.repository.MenuRepository
import com.example.foodicsassessment.core.domain.util.Result

class ProductsPagingSource(
    private val repository: MenuRepository,
    private val word: String?,
    private val categoryId: Int?,
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: 1
            val result = repository.getProducts(
                page,
                categoryId,
                word,

                )
            when (result) {
                is Result.Error -> {
                    LoadResult.Error(Throwable(result.error.name))
                }

                is Result.Success -> {

                    val list = result.data
                    LoadResult.Page(
                        data = list,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (list.isEmpty()) null else page + 1
                    )
                }
            }
        } catch (e: Exception) {
            Log.d("TAG", "load: ${e.message}")
            LoadResult.Error(e)
        }
    }
}