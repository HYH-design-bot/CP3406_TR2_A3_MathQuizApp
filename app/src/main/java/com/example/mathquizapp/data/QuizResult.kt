package com.example.mathquizapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_results")
data class QuizResult(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "app_settings")
data class AppSettings(
    @PrimaryKey val id: Int = 1, // Only ever save 1 row of settings
    val isHighContrast: Boolean = false,
    val difficulty: String = "Easy"
)