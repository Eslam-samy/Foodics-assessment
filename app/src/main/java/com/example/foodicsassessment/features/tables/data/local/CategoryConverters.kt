package com.example.foodicsassessment.features.tables.data.local

import androidx.room.TypeConverter
import com.example.foodicsassessment.features.tables.data.model.common.CategoryDto
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class CategoryConverters {

    @TypeConverter
    fun fromCategoryClasses(value: CategoryDto): String {
        return value.let { Json.encodeToString(it) }
    }

    @TypeConverter
    fun toCategoryClasses(value: String): CategoryDto {
        return value.let { Json.decodeFromString(it) }
    }
}
