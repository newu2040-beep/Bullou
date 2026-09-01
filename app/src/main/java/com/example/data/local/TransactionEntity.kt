package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val amount: Double,
    val type: String, // "INCOME" or "EXPENSE"
    val categoryId: Int,
    val dateMillis: Long,
    val note: String,
    val isRecurring: Boolean = false
)
