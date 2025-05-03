package com.nakibul.android.boilerplateproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nakibul.android.boilerplateproject.navigation.SetUpNavGraph
import com.nakibul.android.boilerplateproject.ui.theme.BoilerplateProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoilerplateProjectTheme {
                navController = rememberNavController()
                SetUpNavGraph(navController = navController)
            }
        }
    }
}
