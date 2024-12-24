package com.example.foodicsassessment.features.tables.data.local

import  androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodicsassessment.features.tables.data.model.cart.CartDto
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(cartDto: CartDto)

    @Query("SELECT * FROM cart")
    fun getCart(): Flow<List<CartDto>> // Use Flow to get real-time updates

    @Query("DELETE FROM cart")
    suspend fun clearCart()
}