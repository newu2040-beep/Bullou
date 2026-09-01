package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ui.BullouViewModel
import com.example.ui.components.BullouBottomNavigation
import com.example.ui.components.ScrapbookCard
import com.example.ui.components.ScreenBackground
import com.example.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(viewModel: BullouViewModel, navController: NavController) {
    ScreenBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("Budget Categories", style = MaterialTheme.typography.headlineMedium) },
                    actions = {
                        IconButton(onClick = { /* Add category */ }) {
                            Icon(Icons.Default.Add, contentDescription = "Add Category")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            },
            bottomBar = {
                BullouBottomNavigation(navController = navController, currentRoute = Screen.Budget.route)
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
                    
                    // Summary card
                    ScrapbookCard(
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🐱", style = MaterialTheme.typography.displayLarge)
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text("Your Spending", style = MaterialTheme.typography.titleLarge)
                                Text("Know where your money goes", style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                }
                
                // Example categories
                val categories = listOf(
                    Triple("Food & Dining", 400, 200),
                    Triple("Transport", 300, 150),
                    Triple("Shopping", 350, 300),
                    Triple("Rent & Utilities", 600, 600),
                    Triple("Entertainment", 200, 50)
                )
                
                items(categories.size) { index ->
                    val cat = categories[index]
                    val progress = (cat.third.toFloat() / cat.second.toFloat()).coerceIn(0f, 1f)
                    
                    ScrapbookCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("🏷️")
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(cat.first, style = MaterialTheme.typography.titleMedium)
                                    Text("$${cat.second}", style = MaterialTheme.typography.titleMedium)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                LinearProgressIndicator(
                                    progress = { progress },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(8.dp)
                                        .clip(RoundedCornerShape(4.dp)),
                                    color = MaterialTheme.colorScheme.primary,
                                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
