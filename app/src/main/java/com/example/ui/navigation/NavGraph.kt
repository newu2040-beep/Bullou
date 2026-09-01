package com.example.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ui.BullouViewModel
import com.example.ui.screens.WelcomeScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.BudgetScreen
import com.example.ui.screens.AddTransactionScreen
import com.example.ui.screens.TransactionsScreen
import com.example.ui.screens.CategoriesScreen
import com.example.ui.screens.AnalyticsScreen
import com.example.ui.screens.GoalsScreen
import com.example.ui.screens.SettingsScreen

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object Home : Screen("home")
    object Budget : Screen("budget")
    object AddTransaction : Screen("add_transaction")
    object Transactions : Screen("transactions")
    object Categories : Screen("categories")
    object Analytics : Screen("analytics")
    object Goals : Screen("goals")
    object Settings : Screen("settings")
}

@Composable
fun BullouNavGraph(
    navController: NavHostController,
    viewModel: BullouViewModel,
    startDestination: String = Screen.Welcome.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onGetStarted = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Budget.route) {
            BudgetScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.AddTransaction.route) {
            AddTransactionScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Transactions.route) {
            TransactionsScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Categories.route) {
            CategoriesScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Analytics.route) {
            AnalyticsScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Goals.route) {
            GoalsScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(viewModel = viewModel, navController = navController)
        }
    }
}
