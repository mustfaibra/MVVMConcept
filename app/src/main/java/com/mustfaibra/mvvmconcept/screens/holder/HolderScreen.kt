package com.mustfaibra.mvvmconcept.screens.holder

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.mustfaibra.mvvmconcept.screens.chats.ChatScreen
import com.mustfaibra.mvvmconcept.screens.home.HomeScreen
import com.mustfaibra.mvvmconcept.screens.notifications.NotificationsScreen
import com.mustfaibra.mvvmconcept.screens.splash.SplashScreen
import com.mustfaibra.mvvmconcept.sealed.Screen

@Composable
fun HolderScreen() {
    val navController = rememberNavController()
    val currentDestination = getCurrentDestination(controller = navController)

    val bottomScreens = listOf(
        Screen.Home,
        Screen.Notifications,
        Screen.Chats,
    )
    Scaffold(
        bottomBar = {
            if (currentDestination in bottomScreens.map { it.route }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.primary)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    bottomScreens.forEach { screen ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(screen.route) {
                                        popUpTo(Screen.Home.route){
                                            inclusive = false
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            screen.icon?.let { icon ->
                                Icon(
                                    painter = painterResource(id = icon),
                                    contentDescription = null,
                                )
                                if (currentDestination == screen.route) {
                                    Text(
                                        text = screen.name,
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = Screen.Splash.route
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onSplashFinished = {
                        navController.navigate(
                            route = Screen.Home.route,
                            navOptions = navOptions {
                                popUpTo(Screen.Splash.route) {
                                    this.inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        )
                    }
                )
            }
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Notifications.route) {
                NotificationsScreen()
            }
            composable(Screen.Chats.route) {
                ChatScreen()
            }
        }
    }
}

@Composable
fun getCurrentDestination(controller: NavController): String {
    return controller
        .currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route ?: Screen.Splash.route
}
