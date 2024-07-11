package com.androiddevs.mvvmnewsapp.util

import com.androiddevs.mvvmnewsapp.BuildConfig

object Constant {
    val API_KEY = BuildConfig.NEWS_API_KEY
    const val BASE_URL = "https://newsapi.org"
    const val SEARCH_NEWS_TIME_DELAY = 500L
    const val QUERY_PAGE_SIZE = 20
}
