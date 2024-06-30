package com.androiddevs.mvvmnewsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.androiddevs.mvvmnewsapp.data.api.NewsApi
import com.androiddevs.mvvmnewsapp.data.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.data.model.Article
import com.androiddevs.mvvmnewsapp.data.model.NewsResponse
import retrofit2.Response

class NewsRepositoryImpl(private val api: NewsApi, private val db: ArticleDatabase) : NewsRepository {

    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews( countryCode = countryCode, pageNumber = pageNumber )

    override suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        api.searchForNews(searchQuery = searchQuery, pageNumber = pageNumber)

    override suspend fun upsertArticle(article: Article) =
        db.getArticleDao().upsertArticle(article)

    override fun getSavedNews(): LiveData<List<Article>>  =
        db.getArticleDao().getSavedNews()

    override suspend fun deleteArticle(article: Article) =
        db.getArticleDao().deleteArticle(article)
}