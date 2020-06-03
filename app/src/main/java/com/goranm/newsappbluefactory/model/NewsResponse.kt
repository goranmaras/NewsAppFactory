package com.goranm.newsappbluefactory.model

import com.goranm.newsappbluefactory.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)