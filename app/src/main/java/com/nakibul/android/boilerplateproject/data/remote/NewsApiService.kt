package com.nakibul.android.boilerplateproject.data.remote

import com.nakibul.android.boilerplateproject.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Nakibul Hassan on 05/05/2025.
 * Email: nakibhasan2711@gmail.com
 */

interface NewsApiService {
    @GET("top-headlines")
    suspend fun fetchTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}