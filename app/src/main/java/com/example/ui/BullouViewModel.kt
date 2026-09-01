package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.CategoryEntity
import com.example.data.local.GoalEntity
import com.example.data.local.TransactionEntity
import com.example.data.repository.BullouRepository
import com.example.data.repository.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BullouViewModel(
    private val repository: BullouRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val transactions: StateFlow<List<TransactionEntity>> = repository.allTransactions.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val categories: StateFlow<List<CategoryEntity>> = repository.allCategories.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val goals: StateFlow<List<GoalEntity>> = repository.allGoals.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val compactMode: StateFlow<Boolean> = settingsRepository.compactModeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val currentTheme: StateFlow<String> = settingsRepository.themeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "Original"
    )
    
    val currentCurrency: StateFlow<String> = settingsRepository.currencyFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = "USD"
    )

    fun addTransaction(transaction: TransactionEntity) {
        viewModelScope.launch {
            repository.insertTransaction(transaction)
        }
    }
    
    fun deleteTransaction(id: Int) {
        viewModelScope.launch {
            repository.deleteTransaction(id)
        }
    }

    fun addCategory(category: CategoryEntity) {
        viewModelScope.launch {
            repository.insertCategory(category)
        }
    }

    fun addGoal(goal: GoalEntity) {
        viewModelScope.launch {
            repository.insertGoal(goal)
        }
    }

    fun setCompactMode(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setCompactMode(enabled)
        }
    }

    fun setTheme(themeName: String) {
        viewModelScope.launch {
            settingsRepository.setTheme(themeName)
        }
    }
    
    fun setCurrency(currency: String) {
        viewModelScope.launch {
            settingsRepository.setCurrency(currency)
        }
    }
}
