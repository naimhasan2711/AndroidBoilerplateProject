package com.nakibul.android.boilerplateproject

import com.nakibul.android.boilerplateproject.data.models.Article
import com.nakibul.android.boilerplateproject.data.remote.NewsApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService
) {
    private val apiKey = "64ca4704ac1f4fe5b58a0b5cb785ed3a"
    suspend fun getArticles(): List<Article> {
        return try {
            val response = newsApiService.fetchTopHeadlines(country = "us", apiKey = apiKey)
            if (response.status == "ok") response.articles else emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }
}