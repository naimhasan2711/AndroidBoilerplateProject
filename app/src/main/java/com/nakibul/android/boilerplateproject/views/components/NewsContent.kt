package com.nakibul.android.boilerplateproject.views.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nakibul.android.boilerplateproject.viewmodels.NewsState

@Composable
fun NewsContent(state: State<NewsState>) {
    when {
        state.value.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.value.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${state.value.error}", color = MaterialTheme.colorScheme.error)
            }
        }

        else -> {
            val articles = state.value.articles ?: emptyList()
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
                    ArticleItem(article = article)
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