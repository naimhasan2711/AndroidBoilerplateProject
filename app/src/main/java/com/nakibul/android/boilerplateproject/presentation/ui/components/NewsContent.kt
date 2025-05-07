package com.nakibul.android.boilerplateproject.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.nakibul.android.boilerplateproject.navigation.Screen
import com.nakibul.android.boilerplateproject.presentation.states.NewsState
import java.net.URLEncoder

@Composable
fun NewsContent(state: NewsState, navController: NavController) {
    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${state.error}", color = MaterialTheme.colorScheme.error)
            }
        }

        else -> {
            val articles = state.articlesLocal ?: emptyList()
            LazyColumn(
                modifier = Modifier.fillMaxSize() // Ensure LazyColumn takes available space
            ) {
                // Add the "Total Results" Text as an item
                item {
                    Text(
                        text = "Total Results: ${articles.size}",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
                    )
                }
                // Use items with direct list access for better performance
                items(articles.size) {
                    val article = articles[it]
                    ArticleItem(article = article, onClick = {
                        val articleJson = Gson().toJson(article)
                        val encodedJson = URLEncoder.encode(articleJson, "UTF-8")
                        navController.navigate("${Screen.First.route}/$encodedJson")

                    })
                    if (it < articles.size - 1) {
                        Divider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            thickness = 3.dp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                        )
                    }
                }
            }
        }
    }
}