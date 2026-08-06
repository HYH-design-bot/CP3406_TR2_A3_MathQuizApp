package com.example.mathquizapp.navigation

sealed class Screen(val route: String) {
    object Landing : Screen("landing")
    object Quiz : Screen("quiz")
    object Settings : Screen("settings")
    object Statistics : Screen("statistics")
}