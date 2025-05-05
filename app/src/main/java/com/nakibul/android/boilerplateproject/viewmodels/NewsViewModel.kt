package com.nakibul.android.boilerplateproject.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nakibul.android.boilerplateproject.data.NewsRepository
import com.nakibul.android.boilerplateproject.data.local.ArticleEntity
import com.nakibul.android.boilerplateproject.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing news articles.
 *
 * @property newsRepository The repository to fetch news articles from.
 */

const val TAG = "NewsViewModel"

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(NewsState())
    val state: StateFlow<NewsState> = _state.asStateFlow()
    private var job: Job? = null
    init {
        fetchNewsFromDB()
    }

    fun fetchNews() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d(TAG, "fetchNews: Called()")
                setIsLoading(true)
                val response = newsRepository.getArticles()
                if (response.isNotEmpty()) {
                    Log.d(TAG, "Fetched ${response.size} articles")
                    Log.d(TAG, "Fetched $response")
                    val articleEntities = response.map { it.toArticleEntity() }
                    newsRepository.insertArticle(articleEntities)
                    _state.value = _state.value.copy(
                        articles = response,
                        articlesLocal = articleEntities,
                        isLoading = false
                    )
                } else {
                    Log.d(TAG, "Failed to fetch news")
                    _state.value = _state.value.copy(
                        articles = emptyList(),
                        articlesLocal = emptyList(),
                        isLoading = false,
                        error = "Failed to fetch news"
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching news: ${e.message}")
                _state.value = _state.value.copy(
                    articles = emptyList(),
                    articlesLocal = emptyList(),
                    isLoading = false,
                    error = e.message ?: "Something went wrong"
                )
            }
        }
    }

    fun fetchNewsFromDB() {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d(TAG, "fetchNewsFromDB: Called()")
                setIsLoading(true)
                val response = newsRepository.getAllArticles()
                if (response.isNotEmpty()) {
                    Log.d(TAG, "Fetched ${response.size} articles from DB")
                    _state.value = _state.value.copy(
                        articlesLocal = response,
                        isLoading = false,
                        error = null
                    )
                } else {
                    Log.d(TAG, "No articles found in DB, fetching from API")
                    fetchNews()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching news from DB: ${e.message}")
                _state.value = _state.value.copy(
                    articlesLocal = emptyList(),
                    isLoading = false,
                    error = e.message ?: "Something went wrong"
                )
            }
        }
    }

    private fun Article.toArticleEntity() = ArticleEntity(
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        sourceName = source.name
    )

    fun setIsLoading(value: Boolean) {
        _state.value = _state.value.copy(
            isLoading = value
        )
    }
}
