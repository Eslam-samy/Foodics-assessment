package com.example.foodicsassessment.features.tables.data.local

import  androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodicsassessment.features.tables.data.model.common.CategoryDto

@Dao
interface CategoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryDto>)

    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<CategoryDto>

    @Query("DELETE FROM categories")
    suspend fun clearCategories()
}