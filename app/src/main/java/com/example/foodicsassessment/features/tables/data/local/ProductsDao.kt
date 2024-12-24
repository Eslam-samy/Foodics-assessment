package com.example.foodicsassessment.features.tables.data.local

import  androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodicsassessment.features.tables.data.model.products.ProductDto

@Dao
interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductDto>)

    @Query(
        """
        SELECT * FROM products 
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
        AND (:categoryId IS NULL OR category_id = :categoryId)
        LIMIT :pageSize OFFSET :offset
    """
    )
    suspend fun getProducts(
        name: String?,
        categoryId: Int?,
        pageSize: Int,
        offset: Int
    ): List<ProductDto>

    @Query("DELETE FROM products")
    suspend fun clearDataBase()
}