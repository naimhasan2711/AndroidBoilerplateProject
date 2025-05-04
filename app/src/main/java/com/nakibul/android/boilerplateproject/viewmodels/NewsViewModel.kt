package com.nakibul.android.boilerplateproject.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nakibul.android.boilerplateproject.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val _state = MutableStateFlow(NewsState(isLoading = true))
    val state: StateFlow<NewsState> = _state.asStateFlow()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        Log.d(TAG, "fetchNews: Called()")
        viewModelScope.launch {
            _state.value = NewsState(isLoading = true)
            val response = newsRepository.getArticles()
            _state.value = if (response != null) {
                Log.d(TAG, "Fetched ${response.size} articles")
                Log.d(TAG, "Fetched ${response}")
                NewsState(articles = response, isLoading = false)
            } else {
                Log.d(TAG, "Failed to fetch news")
                NewsState(isLoading = false, error = "Failed to fetch news")
            }
        }
    }
}
