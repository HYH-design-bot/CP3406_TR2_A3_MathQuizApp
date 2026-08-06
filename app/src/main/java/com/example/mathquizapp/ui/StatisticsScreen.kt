package com.example.mathquizapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mathquizapp.data.QuizDao
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import com.example.mathquizapp.data.QuizResult
import kotlinx.coroutines.launch

@Composable
fun StatisticsScreen(navController: NavController, quizDao: QuizDao) {
    val coroutineScope = rememberCoroutineScope()

    // Fetch changes in results
    val pastResults by quizDao.getAllResults().collectAsState(initial = emptyList())

    // Math Calculations to show basic student performance analytics
    val totalQuizzesPlayed = pastResults.size
    val totalCorrect = pastResults.sumOf { it.correctAnswers }
    val totalQuestionsAsked = pastResults.sumOf { it.totalQuestions }
    val averageAccuracy = if (totalQuestionsAsked > 0) {
        ((totalCorrect.toFloat() / totalQuestionsAsked.toFloat()) * 100).toInt()
    } else 0

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Header
        Text(
            text = "Your Progress History",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.semantics { heading() }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Analytics Overview Card Display
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Total Quizzes Played: $totalQuizzesPlayed", style = MaterialTheme.typography.bodyLarge)
                Text("Overall Accuracy Rate: $averageAccuracy%", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Past Attempts Tracker:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Scrollable List of past scores
        LazyColumn(
            modifier = Modifier.weight(1f).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (pastResults.isEmpty()) {
                item {
                    Text(
                        text = "No history found. Complete a math quiz first to see your statistics appear here!",
                        color = androidx.compose.ui.graphics.Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            } else {
                items(pastResults) { result ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .semantics(mergeDescendants = true) {
                                    contentDescription = "Quiz performance record: ${result.correctAnswers} out of ${result.totalQuestions} correct"
                                },
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Quiz Score:", style = MaterialTheme.typography.bodyMedium)
                            Text(
                                text = "${result.correctAnswers} / ${result.totalQuestions}",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Feature to reset data
        TextButton(
            onClick = {
                coroutineScope.launch {
                    quizDao.clearAllHistory()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear All Historical Data", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
            Text("Back to Main Menu")
        }
    }
}