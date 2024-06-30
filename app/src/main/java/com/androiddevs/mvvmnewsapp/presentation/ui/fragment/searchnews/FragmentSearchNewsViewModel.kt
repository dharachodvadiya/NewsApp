package com.androiddevs.mvvmnewsapp.presentation.ui.fragment.searchnews

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.data.model.NewsResponse
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class FragmentSearchNewsViewModel (private val newsRepo: NewsRepository) : ViewModel() {

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var newsPage = 1

    init {
        searchNews("india")
    }
    private fun searchNews(searchQuery: String)
    {
        viewModelScope.launch{
            val response : Response<NewsResponse> =  newsRepo.searchNews(searchQuery, newsPage)
            //safeSearchNewsCall(searchQuery)
            Log.d("aaa search news" , searchNews.value?.data.toString())
        }
    }

    private suspend fun safeSearchNewsCall(searchQuery: String) {
        searchNews.postValue(Resource.Loading())
        try {
            val response = newsRepo.searchNews(searchQuery, newsPage)
            searchNews.postValue(handleSearchNewsResponse(response))
        } catch(t: Throwable) {
            when(t) {
                is IOException -> searchNews.postValue(Resource.Error("Network Failure"))
                else -> searchNews.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->

                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}