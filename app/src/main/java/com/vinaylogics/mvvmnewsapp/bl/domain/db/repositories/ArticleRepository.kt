package com.vinaylogics.mvvmnewsapp.bl.domain.db.repositories

import com.vinaylogics.mvvmnewsapp.bl.domain.api.RetrofitInstance
import com.vinaylogics.mvvmnewsapp.bl.domain.db.databases.ArticleDatabase
import retrofit2.Retrofit

class ArticleRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode = countryCode, pageNumber= pageNumber)

}
