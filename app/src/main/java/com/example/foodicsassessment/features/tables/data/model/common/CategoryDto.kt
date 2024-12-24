package com.example.foodicsassessment.features.tables.data.model.common


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodicsassessment.features.tables.domain.model.common.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "categories")
data class CategoryDto(
    @SerialName("id")
    @PrimaryKey
    val id: Int,
    @SerialName("name")
    val name: String? = null
) {
    fun toCategory(): Category {
        return Category(
            id = id,
            name = name
        )
    }
}