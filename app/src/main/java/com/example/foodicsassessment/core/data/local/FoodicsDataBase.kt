package com.example.foodicsassessment.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.foodicsassessment.features.tables.data.local.CategoriesDao
import com.example.foodicsassessment.features.tables.data.local.CategoryConverters
import com.example.foodicsassessment.features.tables.data.local.ProductConverters
import com.example.foodicsassessment.features.tables.data.local.ProductsDao
import com.example.foodicsassessment.features.tables.data.model.cart.CartDto
import com.example.foodicsassessment.features.tables.data.model.common.CategoryDto
import com.example.foodicsassessment.features.tables.data.model.products.ProductDto


@Database(
    version = 2,
    exportSchema = false,
    entities = [ProductDto::class, CategoryDto::class, CartDto::class]
)
@TypeConverters(CategoryConverters::class, ProductConverters::class)
abstract class FoodicsDataBase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): FoodicsDataBase =
            Room.databaseBuilder(context, FoodicsDataBase::class.java, "foodics_db")
                .addMigrations(MIGRATION_1_2)
                .build()
    }

    abstract fun medicationDao(): ProductsDao
    abstract fun categoryDao(): CategoriesDao
    abstract fun cartDao(): com.example.foodicsassessment.features.tables.data.local.CartDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Example: Add a new column to an existing table
//        database.execSQL("ALTER TABLE your_table_name ADD COLUMN new_column INTEGER DEFAULT 0")
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS `cart` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                `product` TEXT NOT NULL DEFAULT 'undefined', 
                `amount` INTEGER NOT NULL DEFAULT 0)
        """)
    }
}
