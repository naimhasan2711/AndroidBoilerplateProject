package com.nakibul.android.boilerplateproject.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Room database for storing articles.
 */
@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}