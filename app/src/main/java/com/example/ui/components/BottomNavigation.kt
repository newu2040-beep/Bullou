package com.example.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.ui.navigation.Screen

@Composable
fun BullouBottomNavigation(navController: NavController, currentRoute: String?) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        windowInsets = WindowInsets.navigationBars
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == Screen.Home.route,
            onClick = {
                if (currentRoute != Screen.Home.route) {
                    navController.navigate(Screen.Home.route) { popUpTo(Screen.Home.route) { inclusive = true } }
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.primary,
                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                selectedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Plan") },
            label = { Text("Plan") },
            selected = currentRoute == Screen.Budget.route,
            onClick = {
                if (currentRoute != Screen.Budget.route) {
                    navController.navigate(Screen.Budget.route)
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.primary,
                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                selectedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )
        // Add floating action button placeholder space
        NavigationBarItem(
            icon = { },
            label = { },
            selected = false,
            onClick = { },
            enabled = false
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Track") },
            label = { Text("Track") },
            selected = currentRoute == Screen.Transactions.route,
            onClick = {
                if (currentRoute != Screen.Transactions.route) {
                    navController.navigate(Screen.Transactions.route)
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.primary,
                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                selectedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Star, contentDescription = "Goals") },
            label = { Text("Goals") },
            selected = currentRoute == Screen.Goals.route,
            onClick = {
                if (currentRoute != Screen.Goals.route) {
                    navController.navigate(Screen.Goals.route)
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = MaterialTheme.colorScheme.primary,
                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                selectedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}
