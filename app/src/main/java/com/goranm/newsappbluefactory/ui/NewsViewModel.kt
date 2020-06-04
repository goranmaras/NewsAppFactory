package com.goranm.newsappbluefactory.ui

import androidx.lifecycle.ViewModel
import com.goranm.newsappbluefactory.repository.NewsRepository

class NewsViewModel(
    val newsRepository : NewsRepository
) : ViewModel() {
}