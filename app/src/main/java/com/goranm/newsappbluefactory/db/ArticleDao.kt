package com.goranm.newsappbluefactory.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.goranm.newsappbluefactory.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

    //saveing all
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertList(articles: List<Article>)
}