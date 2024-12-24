package com.example.foodicsassessment.features.tables.data.local

import androidx.room.TypeConverter
import com.example.foodicsassessment.features.tables.data.model.common.CategoryDto
import com.example.foodicsassessment.features.tables.data.model.products.ProductDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class ProductConverters {

    @TypeConverter
    fun fromProductClasses(value: ProductDto): String {
        return value.let { Json.encodeToString(it) }
    }

    @TypeConverter
    fun toProductClasses(value: String): ProductDto {
        return value.let { Json.decodeFromString(it) }
    }
}
