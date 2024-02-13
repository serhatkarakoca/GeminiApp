package com.karakoca.geminiapp.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.karakoca.geminiapp.model.Constants
import com.karakoca.geminiapp.util.Screen
import com.karakoca.geminiapp.util.destinations
import com.karakoca.geminiapp.view.screen.chat.ChatScreen
import com.karakoca.geminiapp.view.commonui.MyToolbar
import com.karakoca.geminiapp.view.screen.home.HomeScreen

@Composable
fun MainNavController() {
    val navController = rememberNavController()
    val toolbarInvisible = listOf<Screen>()

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination =
        destinations.find {
            it.route == currentBackStack?.destination?.route?.substringBefore("?")
                ?.substringBefore("/")
        } ?: Screen.HomeScreen

    Scaffold(topBar = {
        if (toolbarInvisible.find { currentDestination.route.substringBefore("?") == it.route } == null)
            MyToolbar(
                currentDestination.route == Screen.HomeScreen.route,
                stringResource(id = currentDestination.title),
                backStack = { navController.popBackStack() }
            )
    }) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Screen.HomeScreen.route
        ) {
            //Home Screen
            composable(Screen.HomeScreen.route) {
                HomeScreen(navigation = {
                    navController.navigate(it)
                })
            }

            //Chat Screen
            composable(Screen.ChatScreen.route + "?${Constants.CHAT_TYPE_COMMON}={${Constants.CHAT_TYPE_COMMON}}",
                arguments = listOf(
                    navArgument(Constants.CHAT_TYPE_COMMON) {
                        type = NavType.BoolType
                        defaultValue = true
                    }))
            {
                ChatScreen()
            }

        }
    }
}