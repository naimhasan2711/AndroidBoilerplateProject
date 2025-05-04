package com.nakibul.android.boilerplateproject.data.remote

import com.nakibul.android.boilerplateproject.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Nakibul Islam on 10/10/2023.
 * Email:
 *
 *
 *
*/

interface NewsApiService {
    @GET("top-headlines")
    suspend fun fetchTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse
}