package com.shan.jetpack

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
}