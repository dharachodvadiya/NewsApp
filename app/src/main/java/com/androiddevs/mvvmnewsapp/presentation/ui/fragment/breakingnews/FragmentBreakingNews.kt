package com.androiddevs.mvvmnewsapp.presentation.ui.fragment.breakingnews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.mvvmnewsapp.AppModule
import com.androiddevs.mvvmnewsapp.MyApplication
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.data.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.presentation.ui.fragment.ViewModelfactory

class FragmentBreakingNews : Fragment(R.layout.fragment_breaking_news) {

    lateinit var viewModel: FragmentBreakingNewsViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelProviderFactory = ViewModelfactory(AppModule.provideNewsRepository(AppModule.provideNewsApi(), MyApplication().dataBase))
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(FragmentBreakingNewsViewModel::class.java)

    }
}