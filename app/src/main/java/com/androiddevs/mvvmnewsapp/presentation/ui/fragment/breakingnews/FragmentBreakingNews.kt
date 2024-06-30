package com.androiddevs.mvvmnewsapp.presentation.ui.fragment.breakingnews

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.mvvmnewsapp.AppModule
import com.androiddevs.mvvmnewsapp.MyApplication
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.data.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.presentation.adapter.NewsAdapter
import com.androiddevs.mvvmnewsapp.presentation.ui.fragment.ViewModelfactory
import com.androiddevs.mvvmnewsapp.util.Constant.QUERY_PAGE_SIZE
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.paginationProgressBar
import kotlinx.android.synthetic.main.fragment_breaking_news.rvBreakingNews

class FragmentBreakingNews : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: FragmentBreakingNewsViewModel
    lateinit var newsAdapter: NewsAdapter

    val TAG = "BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelProviderFactory = ViewModelfactory(AppModule.provideNewsRepository(AppModule.provideNewsApi(), AppModule.provideDatabase(activity!!.baseContext)))
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(FragmentBreakingNewsViewModel::class.java)

        setupRecyclerView()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_fragmentBreakingNews2_to_fragmentArticle,
                bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    hideErrorMessage()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.newsPage == totalPages
                        if(isLastPage) {
                            rvBreakingNews.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                        showErrorMessage(message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideErrorMessage() {
        //itemErrorMessage.visibility = View.INVISIBLE
        isError = false
    }

    private fun showErrorMessage(message: String) {
       // itemErrorMessage.visibility = View.VISIBLE
       // tvErrorMessage.text = message
        isError = true
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            //addOnScrollListener(this@FragmentBreakingNews.scrollListener)
        }
    }
}