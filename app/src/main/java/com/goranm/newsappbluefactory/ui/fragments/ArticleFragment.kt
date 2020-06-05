package com.goranm.newsappbluefactory.ui.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.goranm.newsappbluefactory.R
import com.goranm.newsappbluefactory.ui.NewsActivity
import com.goranm.newsappbluefactory.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(R.layout.fragment_article){

    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        val article = args.article
        if (article==null){
            val builder = AlertDialog.Builder(context)
            builder.setCancelable(true)
            builder.setMessage("Došlo je do greške")

            builder.setNegativeButton("U redu", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            builder.show()
        }else{
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
        }

    }

}