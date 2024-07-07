package com.androiddevs.mvvmnewsapp.repository

import androidx.lifecycle.LiveData
import com.androiddevs.mvvmnewsapp.data.api.NewsApi
import com.androiddevs.mvvmnewsapp.data.model.Article
import com.androiddevs.mvvmnewsapp.data.model.NewsResponse
import retrofit2.Response

interface NewsRepository {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) : Response<NewsResponse>

    suspend fun searchNews(searchQuery: String, pageNumber: Int) : Response<NewsResponse>

    suspend fun upsertArticle(article: Article) : Long

    fun getSavedNews() : LiveData<List<Article>>

    suspend fun deleteArticle(article: Article)
}