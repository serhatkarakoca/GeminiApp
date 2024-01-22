package com.karakoca.geminiapp.util

sealed class Screen(val route: String) {
    data object HomeScreen: Screen("homeScreen")
}