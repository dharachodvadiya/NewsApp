package com.androiddevs.mvvmnewsapp.presentation.ui.fragment.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.data.model.Article
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class FragmentArticleViewModel(private val newsRepo: NewsRepository) : ViewModel() {

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepo.upsertArticle(article)
    }
}