package com.example.mathquizapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import com.example.mathquizapp.data.AppSettings
import com.example.mathquizapp.data.QuizDao
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(navController: NavController, quizDao: QuizDao) {
    val coroutineScope = rememberCoroutineScope()

    // Persistent Setting
    val savedSettings by quizDao.getSettings().collectAsState(initial = AppSettings())

    // Fallback Protection
    val currentSettings = savedSettings ?: AppSettings()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Header
            Text(
                text = "⚙️ App Preferences",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.semantics { heading() }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Accessibility Setting
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("High Contrast Text", style = MaterialTheme.typography.bodyLarge)
                        Text(
                            "Maximises legibility for low-vision children",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    }
                    Switch(
                        checked = currentSettings.isHighContrast,
                        onCheckedChange = { isChecked ->
                            coroutineScope.launch {
                                quizDao.saveSettings(currentSettings.copy(isHighContrast = isChecked))
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Difficulty Setting
            Text(
                text = "Quiz Difficulty Target:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val difficulties = listOf("Easy", "Medium", "Hard")
                difficulties.forEach { level ->
                    val isSelected = currentSettings.difficulty == level
                    ElevatedButton(
                        onClick = {
                            coroutineScope.launch {
                                quizDao.saveSettings(currentSettings.copy(difficulty = level))
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .heightIn(min = 52.dp),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                            contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(level, style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }

        // Save Button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 52.dp)
        ) {
            Text("Save & Back to Main Menu", style = MaterialTheme.typography.titleSmall)
        }
    }
}