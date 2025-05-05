package com.nakibul.android.boilerplateproject.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data Access Object for ArticleEntity operations.
 */
@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<ArticleEntity>

    @Query("DELETE FROM articles")
    suspend fun clearArticles()
}