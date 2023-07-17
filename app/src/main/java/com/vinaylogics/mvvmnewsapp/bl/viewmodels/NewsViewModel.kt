package com.vinaylogics.mvvmnewsapp.bl.viewmodels

import androidx.lifecycle.ViewModel
import com.vinaylogics.mvvmnewsapp.bl.domain.db.repositories.ArticleRepository

class NewsViewModel(
    val articleRepository: ArticleRepository
) : ViewModel() {
}