package com.androiddevs.mvvmnewsapp.presentation.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.mvvmnewsapp.presentation.ui.fragment.breakingnews.FragmentBreakingNewsViewModel
import com.androiddevs.mvvmnewsapp.presentation.ui.fragment.searchnews.FragmentSearchNewsViewModel
import com.androiddevs.mvvmnewsapp.repository.NewsRepository

class ViewModelfactory(
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass) {
            FragmentBreakingNewsViewModel::class.java -> FragmentBreakingNewsViewModel(newsRepository) as T
            FragmentSearchNewsViewModel::class.java -> FragmentSearchNewsViewModel(newsRepository) as T
            else -> throw Throwable("Unsupported view model")
        }
    }
}