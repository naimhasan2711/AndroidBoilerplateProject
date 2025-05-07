package com.nakibul.android.boilerplateproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.nakibul.android.boilerplateproject.domain.model.Article
import com.nakibul.android.boilerplateproject.presentation.ui.screens.FirstScreen
import com.nakibul.android.boilerplateproject.presentation.ui.screens.HomeScreen
import com.nakibul.android.boilerplateproject.presentation.ui.screens.SecondScreen
import java.net.URLDecoder

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
            route = "${Screen.First.route}/{articleJson}",
            arguments = listOf(
                navArgument("articleJson") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val articleJson = backStackEntry.arguments?.getString("articleJson")
            val decodedJson = articleJson?.let { URLDecoder.decode(it, "UTF-8") }
            val article = decodedJson?.let { Gson().fromJson(it, Article::class.java) }
            FirstScreen(navController = navController, article = article)
        }

        composable(
            route = Screen.Second.route
        ) {
            SecondScreen(navController = navController)
        }

    }
}