package com.vinaylogics.mvvmnewsapp.bl.domain.db.repositories

import com.vinaylogics.mvvmnewsapp.bl.domain.api.RetrofitInstance
import com.vinaylogics.mvvmnewsapp.bl.domain.db.databases.ArticleDatabase
import com.vinaylogics.mvvmnewsapp.bl.domain.models.Article
import retrofit2.Retrofit

class ArticleRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode = countryCode, pageNumber= pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery= searchQuery, pageNumber=pageNumber)

    suspend fun upsert(article:Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

}
