package com.goranm.newsappbluefactory.repository

import androidx.lifecycle.LiveData
import com.goranm.newsappbluefactory.api.RetrofitInstance
import com.goranm.newsappbluefactory.db.ArticleDatabase
import com.goranm.newsappbluefactory.model.Article

class NewsRepository(
    val db : ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun upsertAll(articles: List<Article>) = db.getArticleDao().upsertList(articles)

    fun getNews() = db.getArticleDao().getAllArticles()

}