package com.example.mathquizapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mathquizapp.navigation.Screen
import com.example.mathquizapp.network.MathApiService

@Composable
fun LandingScreen(navController: NavController, apiService: MathApiService) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("📐 Math Quiz App Main Menu", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate(Screen.Quiz.route) }) { Text("Start Quiz") }
        Button(onClick = { navController.navigate(Screen.Statistics.route) }) { Text("View Stats") }
        Button(onClick = { navController.navigate(Screen.Settings.route) }) { Text("Settings") }
    }
}
