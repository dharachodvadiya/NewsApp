package com.androiddevs.mvvmnewsapp

import android.util.Log
import com.androiddevs.mvvmnewsapp.data.api.NewsApi
import com.androiddevs.mvvmnewsapp.data.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.repository.NewsRepositoryImpl
import com.androiddevs.mvvmnewsapp.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

   /* private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
    val repo: NewsRepository by lazy {
        NewsRepositoryImpl(api)
    }*/

    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    fun provideNewsRepository(api: NewsApi, db: ArticleDatabase): NewsRepository {
        return NewsRepositoryImpl(api, db)
    }

}