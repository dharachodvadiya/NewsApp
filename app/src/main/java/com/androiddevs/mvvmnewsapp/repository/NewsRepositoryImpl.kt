package com.androiddevs.mvvmnewsapp.repository

import android.util.Log
import com.androiddevs.mvvmnewsapp.data.api.NewsApi
import com.androiddevs.mvvmnewsapp.data.model.NewsResponse
import retrofit2.Response

class NewsRepositoryImpl(private val api: NewsApi) : NewsRepository {
    override suspend fun getBreakingNews(): Response<NewsResponse> {
        return  api.getBreakingNews()
    }
}