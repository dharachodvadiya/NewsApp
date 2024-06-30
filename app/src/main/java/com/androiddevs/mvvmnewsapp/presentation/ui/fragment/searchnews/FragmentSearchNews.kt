package com.androiddevs.mvvmnewsapp.presentation.ui.fragment.searchnews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.mvvmnewsapp.AppModule
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.presentation.ui.fragment.ViewModelfactory
import com.androiddevs.mvvmnewsapp.presentation.ui.fragment.breakingnews.FragmentBreakingNewsViewModel

class FragmentSearchNews : Fragment(R.layout.fragment_search_news) {

    lateinit var viewModel: FragmentSearchNewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelProviderFactory = ViewModelfactory(AppModule.provideNewsRepository(AppModule.provideNewsApi()))
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(FragmentSearchNewsViewModel::class.java)

    }
}