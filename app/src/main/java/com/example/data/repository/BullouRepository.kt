package com.example.data.repository

import com.example.data.local.BullouDao
import com.example.data.local.CategoryEntity
import com.example.data.local.GoalEntity
import com.example.data.local.TransactionEntity
import kotlinx.coroutines.flow.Flow

class BullouRepository(private val dao: BullouDao) {
    val allTransactions: Flow<List<TransactionEntity>> = dao.getAllTransactions()
    val allCategories: Flow<List<CategoryEntity>> = dao.getAllCategories()
    val allGoals: Flow<List<GoalEntity>> = dao.getAllGoals()

    suspend fun insertTransaction(transaction: TransactionEntity) = dao.insertTransaction(transaction)
    suspend fun insertCategory(category: CategoryEntity) = dao.insertCategory(category)
    suspend fun insertGoal(goal: GoalEntity) = dao.insertGoal(goal)
    suspend fun deleteTransaction(id: Int) = dao.deleteTransactionById(id)
}
