package com.androiddevs.mvvmnewsapp.presentation.ui.fragment.breakingNews

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.AppModule
import com.androiddevs.mvvmnewsapp.data.model.NewsResponse
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class FragmentBreakingNewsViewModel(private val newsRepo: NewsRepository) : ViewModel() {

    init {
        getBreakingNews()
    }
    private fun getBreakingNews()
    {

        viewModelScope.launch{
            val response : Response<NewsResponse> = newsRepo.getBreakingNews()

        }

    }

    /*companion object {
        val Factory: ViewModelProvider.Factory by lazy { NewsViewModelProviderFactory(AppModule.repo) }
    }*/
}

class NewsViewModelProviderFactory(
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FragmentBreakingNewsViewModel(newsRepository) as T
    }
}