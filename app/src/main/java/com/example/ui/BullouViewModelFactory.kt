package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.repository.BullouRepository
import com.example.data.repository.SettingsRepository

class BullouViewModelFactory(
    private val repository: BullouRepository,
    private val settingsRepository: SettingsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BullouViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BullouViewModel(repository, settingsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
