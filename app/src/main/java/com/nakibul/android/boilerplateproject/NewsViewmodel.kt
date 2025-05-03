package com.nakibul.android.boilerplateproject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nakibul.android.boilerplateproject.data.models.Article
import com.nakibul.android.boilerplateproject.data.models.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewmodel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(NewsState(isLoading = true))
    val state: StateFlow<NewsState> = _state.asStateFlow()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        Log.d("NewsViewmodel", "fetchNews: Called()")
        viewModelScope.launch {
            _state.value = NewsState(isLoading = true)
            val response = newsRepository.getArticles()
            _state.value = if (response != null) {
                Log.d("NewsViewmodel", "Fetched ${response.size} articles")
                Log.d("NewsViewmodel", "Fetched ${response}")
                NewsState(articles = response, isLoading = false)
            } else {
                Log.d("NewsViewmodel", "Failed to fetch news")
                NewsState(isLoading = false, error = "Failed to fetch news")
            }
        }
    }
}

data class NewsState(
    val newsResponse: NewsResponse? = null,
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)