package com.karakoca.geminiapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karakoca.geminiapp.util.Screen
import com.karakoca.geminiapp.view.home.HomeScreen

@Composable
fun MainNavController() {
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(navController = navController, startDestination = Screen.HomeScreen.route ) {
            composable(Screen.HomeScreen.route) {
                HomeScreen()
            }
        }
    }
}