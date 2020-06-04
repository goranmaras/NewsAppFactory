package com.goranm.newsappbluefactory.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.goranm.newsappbluefactory.R
import com.goranm.newsappbluefactory.adapter.NewsAdapter
import com.goranm.newsappbluefactory.ui.NewsActivity
import com.goranm.newsappbluefactory.ui.NewsViewModel
import com.goranm.newsappbluefactory.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news){

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "BreakingNewsFragment"

    var currentTime1 = System.currentTimeMillis()
    val currentTime2 = System.currentTimeMillis()
    var after5min = TimeUnit.MINUTES.toMillis(5)
    val currentafter5min = currentTime2 + after5min

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel

        Log.d(TAG, "Ovo je current $currentTime2 a ovo je after5min $after5min")

        setUpRecView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {response ->

            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)

                        GlobalScope.launch(Dispatchers.Unconfined) {
                            viewModel.saveArticles(newsResponse.articles)
                        }

                        if(currentTime1 == currentafter5min) {
                            GlobalScope.launch(Dispatchers.Unconfined) {
                                viewModel.saveArticles(newsResponse.articles)
                            }
                            Log.d(TAG,"Proslo je 5 min, saveaj")
                        }

                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let { message->
                        Log.e(TAG, "Error se dogodio: $message")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }

        })
    }

    private fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setUpRecView(){
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}