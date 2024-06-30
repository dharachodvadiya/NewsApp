package com.androiddevs.mvvmnewsapp.repository

import com.androiddevs.mvvmnewsapp.data.api.NewsApi
import com.androiddevs.mvvmnewsapp.data.model.NewsResponse
import retrofit2.Response

interface NewsRepository {

    suspend fun getBreakingNews() : Response<NewsResponse>
}