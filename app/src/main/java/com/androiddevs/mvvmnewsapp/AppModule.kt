package com.androiddevs.mvvmnewsapp

import android.content.Context
import com.androiddevs.mvvmnewsapp.data.api.NewsApi
import com.androiddevs.mvvmnewsapp.data.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.repository.NewsRepository
import com.androiddevs.mvvmnewsapp.repository.NewsRepositoryImpl
import com.androiddevs.mvvmnewsapp.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val api: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
    private val repo: NewsRepository by lazy {
        NewsRepositoryImpl(api, db)
    }

    private val db: ArticleDatabase by lazy {
        ArticleDatabase(MyApplication.instance)
    }

    fun provideNewsApi(): NewsApi {
        /*return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)*/

        return api;
    }

    fun provideNewsRepository(api: NewsApi, db: ArticleDatabase): NewsRepository {
       // return NewsRepositoryImpl(api, db)
        return  repo
    }

    fun provideDatabase(context: Context) : ArticleDatabase{
        //return ArticleDatabase(context)
        return db
    }

}