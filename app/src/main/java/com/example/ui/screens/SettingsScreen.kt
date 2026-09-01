package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.ui.BullouViewModel
import com.example.ui.components.ScrapbookCard
import com.example.ui.components.ScreenBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: BullouViewModel, navController: NavController) {
    val compactMode by viewModel.compactMode.collectAsStateWithLifecycle()
    val currentTheme by viewModel.currentTheme.collectAsStateWithLifecycle()
    val currency by viewModel.currentCurrency.collectAsStateWithLifecycle()
    
    val themes = listOf("Original", "Strawberry Milk", "Matcha", "Blueberry", "Cherry", "Vanilla", "Daisy", "Lavender")
    val currencies = listOf("USD", "GBP", "EUR", "JPY", "INR", "NPR", "CNY", "AUD", "CAD", "SGD", "AED", "SAR", "KRW", "CHF")
    
    var themeExpanded by remember { mutableStateOf(false) }
    var currencyExpanded by remember { mutableStateOf(false) }

    ScreenBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("Settings", style = MaterialTheme.typography.headlineMedium) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text("Display", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 8.dp))
                    
                    ScrapbookCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text("Compact Mode", style = MaterialTheme.typography.titleMedium)
                                    Text("Reduce spacing and sizes", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                                }
                                Switch(
                                    checked = compactMode,
                                    onCheckedChange = { viewModel.setCompactMode(it) },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                                        checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
                                    )
                                )
                            }
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            ExposedDropdownMenuBox(
                                expanded = themeExpanded,
                                onExpandedChange = { themeExpanded = !themeExpanded }
                            ) {
                                TextField(
                                    readOnly = true,
                                    value = currentTheme,
                                    onValueChange = { },
                                    label = { Text("App Theme") },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = themeExpanded) },
                                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent
                                    ),
                                    modifier = Modifier.menuAnchor().fillMaxWidth()
                                )
                                ExposedDropdownMenu(
                                    expanded = themeExpanded,
                                    onDismissRequest = { themeExpanded = false }
                                ) {
                                    themes.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            text = { Text(selectionOption) },
                                            onClick = {
                                                viewModel.setTheme(selectionOption)
                                                themeExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Preferences", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 8.dp))
                    
                    ScrapbookCard(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            ExposedDropdownMenuBox(
                                expanded = currencyExpanded,
                                onExpandedChange = { currencyExpanded = !currencyExpanded }
                            ) {
                                TextField(
                                    readOnly = true,
                                    value = currency,
                                    onValueChange = { },
                                    label = { Text("Primary Currency") },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = currencyExpanded) },
                                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                                        focusedContainerColor = Color.Transparent,
                                        unfocusedContainerColor = Color.Transparent
                                    ),
                                    modifier = Modifier.menuAnchor().fillMaxWidth()
                                )
                                ExposedDropdownMenu(
                                    expanded = currencyExpanded,
                                    onDismissRequest = { currencyExpanded = false }
                                ) {
                                    currencies.forEach { selectionOption ->
                                        DropdownMenuItem(
                                            text = { Text(selectionOption) },
                                            onClick = {
                                                viewModel.setCurrency(selectionOption)
                                                currencyExpanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
