package com.karakoca.geminiapp.util

import com.karakoca.geminiapp.R

sealed class Screen(val route: String, val title: Int) {
    data object HomeScreen : Screen("homeScreen", R.string.home)
    data object ChatScreen : Screen("chatScreen", R.string.chat)
}

val destinations = listOf(Screen.HomeScreen, Screen.ChatScreen)