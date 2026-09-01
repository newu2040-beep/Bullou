package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(viewModel: BullouViewModel, navController: NavController) {
    ScreenBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("Categories", style = MaterialTheme.typography.headlineMedium) },
                    actions = {
                        IconButton(onClick = { /* Add category */ }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            },
            bottomBar = {
                BullouBottomNavigation(navController = navController, currentRoute = "categories") // Not in bottom bar usually, but just in case
            }
        ) { paddingValues ->
            val sampleCategories = listOf(
                "Food & Dining" to "🍕",
                "Transport" to "🚌",
                "Shopping" to "🛍️",
                "Entertainment" to "🎬",
                "Rent & Utilities" to "🏠",
                "Health" to "❤️"
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                items(sampleCategories.size) { index ->
                    val cat = sampleCategories[index]
                    ScrapbookCard(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(cat.second, style = MaterialTheme.typography.headlineLarge)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(cat.first, style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}
