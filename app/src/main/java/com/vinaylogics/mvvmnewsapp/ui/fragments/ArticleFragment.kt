package com.vinaylogics.mvvmnewsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.vinaylogics.mvvmnewsapp.R
import com.vinaylogics.mvvmnewsapp.bl.viewmodels.NewsViewModel
import com.vinaylogics.mvvmnewsapp.databinding.FragmentArticleBinding
import com.vinaylogics.mvvmnewsapp.ui.activities.NewsActivity


/**
 * A simple [Fragment] subclass.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var binding:  FragmentArticleBinding
    private lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel
        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ArticleFragment.
         */
        @JvmStatic
        fun newInstance() =
            ArticleFragment()
    }
}