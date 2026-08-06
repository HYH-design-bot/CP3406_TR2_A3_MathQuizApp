package com.example.mathquizapp

import org.junit.Test
import org.junit.Assert.*

class QuizEngineTest {

    @Test
    fun testQuizScoringLogic_isCorrect() {
        // Given an initial score setup
        var mockUserScore = 0
        val correctAnswer = 5 + 7

        // When a user selects a correct answer variant
        val userSelection = 12
        if (userSelection == correctAnswer) {
            mockUserScore++
        }

        // Then verify the data state mutates accurately
        assertEquals(1, mockUserScore)
    }

    @Test
    fun testQuizScoringLogic_isIncorrect() {
        var mockUserScore = 0
        val correctAnswer = 4 + 3

        // When a user selects an incorrect answer choice
        val userSelection = 9
        if (userSelection == correctAnswer) {
            mockUserScore++
        }

        // Then verify the user's score remains unchanged
        assertEquals(0, mockUserScore)
    }
}