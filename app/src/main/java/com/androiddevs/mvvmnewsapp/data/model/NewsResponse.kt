package com.androiddevs.mvvmnewsapp.data.model

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)