package com.vinaylogics.mvvmnewsapp.ui.viewmodelproviders

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vinaylogics.mvvmnewsapp.bl.domain.db.repositories.ArticleRepository
import com.vinaylogics.mvvmnewsapp.bl.viewmodels.NewsViewModel

class NewsViewModelProviderFactory(
    val app: Application,
   private val articleRepository: ArticleRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app,articleRepository) as T
    }
}