package com.nakibul.android.boilerplateproject.presentation.states

import com.nakibul.android.boilerplateproject.data.local.ArticleEntity
import com.nakibul.android.boilerplateproject.domain.model.Article
import com.nakibul.android.boilerplateproject.domain.model.NewsResponse


/**
 * A data class representing the state of the news screen.
 *
 * @property newsResponse The response from the news API.
 * @property articles A list of articles to be displayed.
 * @property isLoading A boolean indicating whether the data is currently being loaded.
 * @property error An optional error message if an error occurred during data loading.
 */
data class NewsState(
    val newsResponse: NewsResponse? = null,
    val articles: List<Article> = emptyList(),
    val articlesLocal: List<ArticleEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)