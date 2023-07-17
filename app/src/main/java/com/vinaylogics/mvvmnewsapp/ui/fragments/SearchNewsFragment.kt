package com.vinaylogics.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinaylogics.mvvmnewsapp.R
import com.vinaylogics.mvvmnewsapp.bl.domain.utils.Constants
import com.vinaylogics.mvvmnewsapp.bl.domain.utils.Resource
import com.vinaylogics.mvvmnewsapp.bl.viewmodels.NewsViewModel
import com.vinaylogics.mvvmnewsapp.databinding.FragmentSearchNewsBinding
import com.vinaylogics.mvvmnewsapp.ui.activities.NewsActivity
import com.vinaylogics.mvvmnewsapp.ui.adapters.ArticleAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

/**
 * A simple [Fragment] subclass.
 * Use the [SearchNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {

    lateinit var binding: FragmentSearchNewsBinding
    lateinit var viewModel: NewsViewModel
    lateinit var articleAdapter: ArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchNewsBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel

        articleAdapter = ArticleAdapter()
        setupRecyclerView()
        articleAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_search_nf_to_af,
                bundle
            )
        }
        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(Constants.SEARCH_NEWS_TIME_DELAY.milliseconds)
                editable?.let {
                    if(editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }
        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        articleAdapter.differ.submitList(newsResponse.articles)

                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occurred: $message")
                    }
                }
                is Resource.Loading-> {
                    showProgressBar()
                }
            }
        })
    }

    private fun setupRecyclerView(){
        articleAdapter = ArticleAdapter()
        binding.rvSearchNews.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }
    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
    companion object {
        private  val TAG = SearchNewsFragment::class.java.simpleName
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment BreakingNewsFragment.
         */
        @JvmStatic
        fun newInstance() =
            SearchNewsFragment()
    }
}