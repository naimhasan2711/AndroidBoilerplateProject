package com.nakibul.android.boilerplateproject.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.nakibul.android.boilerplateproject.domain.model.Article
import com.nakibul.android.boilerplateproject.ui.theme.Pink401
import com.nakibul.android.boilerplateproject.utils.formatPublishedAtDate

@Composable
fun NewsDetailsScreen(modifier: Modifier = Modifier, article: Article?) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Pink401,
            )
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Pink401,
                    shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp)
                )
                .align(Alignment.TopCenter),
        ) {
            Column(
                modifier = Modifier
                    .background(color = Pink401)
                    .padding(vertical = 0.dp, horizontal = 16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = article?.title ?: "No author",
                    color = Color.White,
                    fontWeight = FontWeight.W900,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(top = 32.dp),
                    lineHeight = 32.sp,
                    letterSpacing = 3.sp
                )
                Text(
                    text = article?.author ?: "No author",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W900,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = "Published at: " + formatPublishedAtDate(article?.publishedAt.toString()),
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 12.dp),
                    fontSize = 16.sp,
                )

                Image(
                    painter = rememberImagePainter(data = article?.urlToImage),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp)) // Add rounded corners
                )

                Text(
                    text = article?.content ?: "No content",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 12.dp, top = 12.dp),
                    fontSize = 16.sp,
                )
            }
        }
    }

}