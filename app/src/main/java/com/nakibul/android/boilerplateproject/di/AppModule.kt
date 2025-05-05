package com.nakibul.android.boilerplateproject.di

import android.content.Context
import androidx.room.Room
import com.nakibul.android.boilerplateproject.data.local.AppDatabase
import com.nakibul.android.boilerplateproject.data.local.ArticleDao
import com.nakibul.android.boilerplateproject.data.remote.NewsApiService
import com.nakibul.android.boilerplateproject.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dependency Injection module for providing Retrofit and API service instances.
 * This module is responsible for creating and providing the necessary dependencies
 * for the application, such as Retrofit and the NewsApiService.
 */


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = Interceptor { chain ->
            val packageName =
                "com.nakibul.android.boilerplateproject" // Replace with your app's package name
            val request = chain.request().newBuilder()
                .addHeader("App-Package-Name", packageName) // Add package name to the header
                .build()
            chain.proceed(request)
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Add logging interceptor
            .addInterceptor(headerInterceptor)  // Add custom header interceptor
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient) // Attach OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(database: AppDatabase): ArticleDao {
        return database.articleDao()
    }
}