package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.R
import com.example.ui.BullouViewModel
import com.example.ui.components.BullouBottomNavigation
import com.example.ui.components.ScrapbookCard
import com.example.ui.components.ScreenBackground
import com.example.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: BullouViewModel, navController: NavController) {
    val currency by viewModel.currentCurrency.collectAsStateWithLifecycle()
    
    ScreenBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { 
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Bullou", style = MaterialTheme.typography.headlineMedium)
                            Spacer(modifier = Modifier.width(8.dp))
                            Image(
                                painter = painterResource(id = R.drawable.sunflower_sticker),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { /* Open drawer */ }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                            Icon(Icons.Default.Settings, contentDescription = "Settings")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )
            },
            bottomBar = {
                BullouBottomNavigation(navController = navController, currentRoute = Screen.Home.route)
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.AddTransaction.route) },
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Transaction")
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Balance Card
                    ScrapbookCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text("This Month", style = MaterialTheme.typography.labelMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("$1,240", style = MaterialTheme.typography.displayLarge)
                            Text("of $2,000 budget", style = MaterialTheme.typography.bodyMedium)
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                // Income
                                ScrapbookCard(
                                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                                    elevation = 0.dp,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Column {
                                        Text("Income", style = MaterialTheme.typography.labelMedium)
                                        Text("$1,500", style = MaterialTheme.typography.titleLarge)
                                    }
                                }
                                
                                Spacer(modifier = Modifier.width(16.dp))
                                
                                // Expenses
                                ScrapbookCard(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                    elevation = 0.dp,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Column {
                                        Text("Expenses", style = MaterialTheme.typography.labelMedium)
                                        Text("$260", style = MaterialTheme.typography.titleLarge)
                                    }
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Recent Expenses", style = MaterialTheme.typography.titleLarge)
                        Text(
                            "View All",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                }
                
                // Dummy recent expenses
                items(3) { index ->
                    ScrapbookCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Icon placeholder
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("☕️")
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text("Food & Dining", style = MaterialTheme.typography.titleMedium)
                                    Text("Yesterday", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                                }
                            }
                            Text("-$12.50", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}
