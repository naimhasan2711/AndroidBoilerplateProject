package com.nakibul.android.boilerplateproject.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object First : Screen(route = "first_screen")
    object Second : Screen(route = "second_screen")
}