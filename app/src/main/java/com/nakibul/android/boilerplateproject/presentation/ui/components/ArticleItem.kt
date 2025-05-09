package com.nakibul.android.boilerplateproject.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.nakibul.android.boilerplateproject.data.local.ArticleEntity
import com.nakibul.android.boilerplateproject.ui.theme.Pink401
import com.nakibul.android.boilerplateproject.utils.formatPublishedAtDate

@Composable
fun ArticleItem(article: ArticleEntity, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .background(color = Pink401)
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick() }
    ) {
        // Placeholder for an image or icon
        // You can use an Image composable here if you have a URL or resource
        Image(
            painter = rememberImagePainter(data = article.urlToImage),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(150.dp)
                .width(150.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)) // Add rounded corners
        )
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = article.author ?: "No author",
                color = Color.White,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = article.title ?: "No title",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp),
                maxLines = 3,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                fontSize = 24.sp
            )
            Row(modifier = Modifier) {
                Text(
                    text = article.author ?: "Unknown",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = formatPublishedAtDate(article.publishedAt.toString()) ?: "Unknown",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
