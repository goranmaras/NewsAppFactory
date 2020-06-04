package com.goranm.newsappbluefactory.repository

import com.goranm.newsappbluefactory.api.RetrofitInstance
import com.goranm.newsappbluefactory.db.ArticleDatabase

class NewsRepository(
    val db : ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

}