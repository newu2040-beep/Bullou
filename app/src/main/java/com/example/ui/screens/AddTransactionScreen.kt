package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.local.TransactionEntity
import com.example.ui.BullouViewModel
import com.example.ui.components.PillButton
import com.example.ui.components.ScrapbookCard
import com.example.ui.components.ScreenBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(viewModel: BullouViewModel, navController: NavController) {
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var isExpense by remember { mutableStateOf(true) }
    
    ScreenBackground {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("Add Transaction", style = MaterialTheme.typography.headlineMedium) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                
                // Toggle Type
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ScrapbookCard(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { isExpense = true },
                        color = if (isExpense) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        elevation = if (isExpense) 4.dp else 0.dp
                    ) {
                        Text(
                            "Expense",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            color = if (isExpense) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    ScrapbookCard(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { isExpense = false },
                        color = if (!isExpense) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surface,
                        elevation = if (!isExpense) 4.dp else 0.dp
                    ) {
                        Text(
                            "Income",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            color = if (!isExpense) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Amount Input
                Text("Amount", style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    leadingIcon = { Text("$", style = MaterialTheme.typography.titleLarge) },
                    textStyle = MaterialTheme.typography.displayMedium
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Category Input Placeholder
                Text("Category", style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.height(8.dp))
                ScrapbookCard(modifier = Modifier.fillMaxWidth()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("☕️")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text("Food & Dining", style = MaterialTheme.typography.titleMedium)
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Note Input
                Text("Note (Optional)", style = MaterialTheme.typography.labelLarge)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                PillButton(
                    text = "Save Transaction",
                    icon = Icons.Default.Check,
                    onClick = {
                        val amountVal = amount.toDoubleOrNull() ?: 0.0
                        if (amountVal > 0) {
                            viewModel.addTransaction(
                                TransactionEntity(
                                    amount = amountVal,
                                    type = if (isExpense) "EXPENSE" else "INCOME",
                                    categoryId = 1,
                                    dateMillis = System.currentTimeMillis(),
                                    note = note
                                )
                            )
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
