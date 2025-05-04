package com.nakibul.android.boilerplateproject.views.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nakibul.android.boilerplateproject.models.Article

@Composable
fun ArticleItem(article: Article) {
    Text(
        text = article.title ?: "No Title",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 8.dp)
    )
    Text(
        text = "Source: ${article.source.name ?: "Unknown"}",
        style = MaterialTheme.typography.bodySmall
    )
    Text(
        text = "Published: ${article.publishedAt ?: "Unknown"}",
        style = MaterialTheme.typography.bodySmall
    )
}