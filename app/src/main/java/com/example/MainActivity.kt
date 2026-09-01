package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.data.local.BullouDatabase
import com.example.data.repository.BullouRepository
import com.example.data.repository.SettingsRepository
import com.example.ui.BullouViewModel
import com.example.ui.BullouViewModelFactory
import com.example.ui.navigation.BullouNavGraph
import com.example.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val database = BullouDatabase.getDatabase(this)
        val repository = BullouRepository(database.bullouDao())
        val settingsRepository = SettingsRepository(this)
        
        val viewModelFactory = BullouViewModelFactory(repository, settingsRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[BullouViewModel::class.java]
        
        setContent {
            AppTheme {
                val navController = rememberNavController()
                BullouNavGraph(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}
