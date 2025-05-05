package com.nakibul.android.boilerplateproject.views.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nakibul.android.boilerplateproject.models.Article
import com.nakibul.android.boilerplateproject.ui.theme.Pink401
import com.nakibul.android.boilerplateproject.views.components.NewsDetailsScreen

@Composable
fun FirstScreen(modifier: Modifier = Modifier, navController: NavController, article: Article?) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Transparent,
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
                .background(
                    color = Pink401,
                    shape = RoundedCornerShape(5.dp, 5.dp, 5.dp, 5.dp)
                )
                .align(Alignment.TopCenter),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NewsDetailsScreen(article = article)
            }
        }
    }
}
