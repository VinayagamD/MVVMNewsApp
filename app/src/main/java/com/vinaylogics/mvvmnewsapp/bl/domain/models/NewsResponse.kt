package com.vinaylogics.mvvmnewsapp.bl.domain.models

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)