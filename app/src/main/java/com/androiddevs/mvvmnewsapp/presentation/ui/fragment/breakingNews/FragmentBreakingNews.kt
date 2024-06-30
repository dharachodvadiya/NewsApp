package com.androiddevs.mvvmnewsapp.presentation.ui.fragment.breakingNews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.mvvmnewsapp.AppModule
import com.androiddevs.mvvmnewsapp.R

class FragmentBreakingNews : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: FragmentBreakingNewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelProviderFactory = NewsViewModelProviderFactory(AppModule.provideNewsRepository(AppModule.provideNewsApi()))
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(FragmentBreakingNewsViewModel::class.java)

    }
}