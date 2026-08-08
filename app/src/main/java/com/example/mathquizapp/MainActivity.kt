package com.example.mathquizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mathquizapp.ui.theme.MathQuizAppTheme
import androidx.navigation.compose.rememberNavController
import com.example.mathquizapp.data.AppDatabase
import com.example.mathquizapp.navigation.AppNavGraph
import com.example.mathquizapp.network.MathApiService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Room Database
        val database = AppDatabase.getDatabase(this)
        val quizDao = database.quizDao()

        // Retrofit remote network client
        val apiService = MathApiService.create()

        setContent {
            MathQuizAppTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Navigation controller routing graph
                    AppNavGraph(
                        navController = navController,
                        quizDao = quizDao,
                        apiService = apiService,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
