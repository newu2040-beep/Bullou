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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.data.local.TransactionEntity
import com.example.ui.BullouViewModel
import com.example.ui.components.BullouBottomNavigation
import com.example.ui.components.ScrapbookCard
import com.example.ui.components.ScreenBackground
import com.example.ui.navigation.Screen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(viewModel: BullouViewModel, navController: NavController) {
    val transactions by viewModel.transactions.collectAsStateWithLifecycle()
    val currency by viewModel.currentCurrency.collectAsStateWithLifecycle()
    val currencySymbol = if (currency == "USD") "$" else if (currency == "GBP") "£" else if (currency == "EUR") "€" else currency
    
    ScreenBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("Transactions", style = MaterialTheme.typography.headlineMedium) },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            },
            bottomBar = {
                BullouBottomNavigation(navController = navController, currentRoute = Screen.Transactions.route)
            }
        ) { paddingValues ->
            if (transactions.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize().padding(paddingValues), contentAlignment = Alignment.Center) {
                    Text("No transactions yet.", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp)
                ) {
                    item { Spacer(modifier = Modifier.height(16.dp)) }
                    items(transactions) { tx ->
                        TransactionItem(tx, currencySymbol)
                    }
                    item { Spacer(modifier = Modifier.height(32.dp)) }
                }
            }
        }
    }
}

@Composable
fun TransactionItem(tx: TransactionEntity, currencySymbol: String) {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    val dateString = dateFormat.format(Date(tx.dateMillis))
    
    ScrapbookCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            if (tx.type == "EXPENSE") MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                            else MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(if (tx.type == "EXPENSE") "💸" else "💰")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        if (tx.note.isNotEmpty()) tx.note else "Transaction",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        dateString,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }
            Text(
                "${if (tx.type == "EXPENSE") "-" else "+"}$currencySymbol${tx.amount}",
                style = MaterialTheme.typography.titleMedium,
                color = if (tx.type == "EXPENSE") MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.secondary
            )
        }
    }
}
