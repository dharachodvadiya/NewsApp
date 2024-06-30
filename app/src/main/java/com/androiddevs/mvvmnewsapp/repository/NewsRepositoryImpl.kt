package com.androiddevs.mvvmnewsapp.repository

import android.util.Log
import com.androiddevs.mvvmnewsapp.data.api.NewsApi
import com.androiddevs.mvvmnewsapp.data.model.NewsResponse
import retrofit2.Response

class NewsRepositoryImpl(private val api: NewsApi) : NewsRepository {

    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int
    ): Response<NewsResponse> {
        return  api.getBreakingNews(
            countryCode = countryCode,
            pageNumber = pageNumber
        )
    }

    override suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> {
        return  api.searchForNews(
            searchQuery = searchQuery,
            pageNumber = pageNumber
        )
    }
}