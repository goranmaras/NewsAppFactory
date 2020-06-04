package com.goranm.newsappbluefactory.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.goranm.newsappbluefactory.R
import com.goranm.newsappbluefactory.ui.NewsActivity
import com.goranm.newsappbluefactory.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article){

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }

}