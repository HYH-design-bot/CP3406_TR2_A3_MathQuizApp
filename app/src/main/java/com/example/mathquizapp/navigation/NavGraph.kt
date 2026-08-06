package com.example.mathquizapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mathquizapp.data.QuizDao
import com.example.mathquizapp.network.MathApiService

@Composable
fun AppNavGraph(
    navController: NavHostController,
    quizDao: QuizDao,
    apiService: MathApiService,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Landing.route,
        modifier = modifier
    ) {
        composable(Screen.Landing.route) {
            com.example.mathquizapp.ui.LandingScreen(navController, apiService)
        }
        composable(Screen.Quiz.route) {
            com.example.mathquizapp.ui.QuizScreen(navController, quizDao)
        }
        composable(Screen.Settings.route) {
            com.example.mathquizapp.ui.SettingsScreen(navController)
        }
        composable(Screen.Statistics.route) {
            com.example.mathquizapp.ui.StatisticsScreen(navController, quizDao)
        }
    }
}