package com.mustfaibra.mvvmconcept.sealed

import com.mustfaibra.mvvmconcept.R

sealed class Screen(val route: String, val name: String, val icon: Int? = null){
    object Splash : Screen(route = "splash", name = "Splash")
    object Home: Screen(route = "home", name = "Home", icon = R.drawable.ic_home)
    object Notifications : Screen(route = "notifications", name = "Notifications", icon = R.drawable.ic_notifications)
    object Chats: Screen(route = "chats", name = "Chats", icon = R.drawable.ic_chat)
}