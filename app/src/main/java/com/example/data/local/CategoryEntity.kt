package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val iconName: String, // name of the icon to map to a drawable/compose icon
    val colorArgb: Int,
    val budgetLimit: Double? = null // For category spending limits
)
