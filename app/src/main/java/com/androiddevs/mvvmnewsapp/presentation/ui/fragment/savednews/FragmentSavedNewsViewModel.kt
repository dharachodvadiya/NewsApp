package com.androiddevs.mvvmnewsapp.presentation.ui.fragment.savednews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.data.model.Article
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import kotlinx.coroutines.launch

class FragmentSavedNewsViewModel(private val newsRepo: NewsRepository) : ViewModel() {

    fun getSavedNews() = newsRepo.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepo.deleteArticle(article)
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepo.upsertArticle(article)
    }

}