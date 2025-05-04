package com.nakibul.android.boilerplateproject.data

import com.nakibul.android.boilerplateproject.data.remote.NewsApiService
import com.nakibul.android.boilerplateproject.models.Article
import com.nakibul.android.boilerplateproject.utils.Constant
import javax.inject.Inject

/**
 * Repository class to fetch news articles from the News API.
 *
 * @param newsApiService The API service to fetch news articles.
 */


class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService
) {
    suspend fun getArticles(): List<Article> {
        return try {
            val response = newsApiService.fetchTopHeadlines(country = "us", apiKey = Constant.API_KEY)
            if (response.status == "ok") response.articles else emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}