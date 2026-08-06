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
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.launch
@Composable
fun LandingScreen(navController: NavController, apiService: MathApiService) {
    val coroutineScope = rememberCoroutineScope()

    // UI state holder to hold the online math fact
    var mathFact by remember { mutableStateOf("Loading interesting math trivia fact...") }

    // Fetch the math fact when the screen loads
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = apiService.getRandomMathFact()
                if (response.found) {
                    mathFact = response.factText
                }
            } catch (e: Exception) {
                mathFact = "Welcome! Ready to exercise your brain with some quick math quiz problems today?"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // App Title Banner
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "📐 Math Blast!",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.semantics { heading() }
            )
            Text(
                text = "Fun Educational App",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
        }

        // Internet Trivia API Container Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(20.dp)
                .semantics { contentDescription = "Daily educational fact container: $mathFact" }
        ) {
            Text(
                text = mathFact,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Navigation Selection Cluster
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.navigate(Screen.Quiz.route) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp)
            ) {
                Text("Start Math Quiz", style = MaterialTheme.typography.labelLarge)
            }

            Button(
                onClick = { navController.navigate(Screen.Statistics.route) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("View My Statistics", style = MaterialTheme.typography.labelLarge)
            }

            OutlinedButton(
                onClick = { navController.navigate(Screen.Settings.route) },
                modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp)
            ) {
                Text("App Preferences", style = MaterialTheme.typography.labelLarge)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}