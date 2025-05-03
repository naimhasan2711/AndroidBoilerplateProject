package com.nakibul.android.boilerplateproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nakibul.android.boilerplateproject.screens.FirstScreen
import com.nakibul.android.boilerplateproject.screens.HomeScreen
import com.nakibul.android.boilerplateproject.screens.SecondScreen

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.First.route
        ) {
            FirstScreen(navController = navController)
        }

        composable(
            route = Screen.Second.route
        ) {
            SecondScreen(navController = navController)
        }

    }
}