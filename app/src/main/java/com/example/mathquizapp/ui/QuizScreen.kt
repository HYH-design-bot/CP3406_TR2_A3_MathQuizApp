package com.example.mathquizapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mathquizapp.data.QuizDao
import androidx.compose.runtime.*
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.example.mathquizapp.data.QuizResult
import kotlinx.coroutines.launch
import kotlin.random.Random
import com.example.mathquizapp.data.AppSettings

@Composable
fun QuizScreen(navController: NavController, quizDao: QuizDao) {
    val coroutineScope = rememberCoroutineScope()

    // 💡 Step A: Observe your stored user settings configurations live from the DB layer rows
    val settingsState by quizDao.getSettings().collectAsState(initial = AppSettings())
    val activeDifficulty = settingsState?.difficulty ?: "Easy"

    // 💡 Step B: Dynamically shift the equation upper bound variable range matching their selection
    val numericRangeBoundary = when (activeDifficulty) {
        "Medium" -> 50
        "Hard" -> 100
        else -> 10 // Easy
    }

    // 💡 Step C: Passing numericRangeBoundary into remember keys triggers recalculation when options change
    var num1 by remember(numericRangeBoundary) { mutableStateOf(Random.nextInt(1, numericRangeBoundary)) }
    var num2 by remember(numericRangeBoundary) { mutableStateOf(Random.nextInt(1, numericRangeBoundary)) }
    var currentQuestion by remember { mutableStateOf(1) }
    var score by remember { mutableStateOf(0) }
    var isQuizFinished by remember { mutableStateOf(false) }

    // Generate correct answer
    val correctAnswer = num1 + num2
    val choicesList = remember(num1, num2) {
        listOf(correctAnswer, correctAnswer + 2, correctAnswer - 1).shuffled()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (!isQuizFinished) {
            // Header Tracker
            Text(
                text = "Question $currentQuestion of 5 ($activeDifficulty Mode)",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.semantics { contentDescription = "Progress: Question $currentQuestion of 5" }
            )

            // Large Equation Display
            Text(
                text = "$num1 + $num2 = ?",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(vertical = 32.dp)
            )

            // Multiple choice grid
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                choicesList.forEach { choice ->
                    Button(
                        onClick = {
                            // Check if choice is correct
                            if (choice == correctAnswer) {
                                score++
                            }

                            // Progress loop check
                            if (currentQuestion < 5) {
                                num1 = Random.nextInt(1, numericRangeBoundary)
                                num2 = Random.nextInt(1, numericRangeBoundary)
                                currentQuestion++
                            } else {
                                isQuizFinished = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp)
                    ) {
                        Text(text = choice.toString(), style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }

            // Early exit safeguard choice
            OutlinedButton(onClick = { navController.popBackStack() }, modifier = Modifier.fillMaxWidth()) {
                Text("Quit Quiz")
            }
        } else {
            // Display Screen When Finish
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Text("Well Done!", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Final Score: $score / 5", style = MaterialTheme.typography.titleLarge)
            }

            // Room Insertion Trigger Button
            Button(
                onClick = {
                    coroutineScope.launch {
                        // Writes the score into Database
                        quizDao.insertResult(QuizResult(totalQuestions = 5, correctAnswers = score))
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth().heightIn(min = 52.dp)
            ) {
                Text("Save & Return to Menu")
            }
        }
    }
}