package com.vinaylogics.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinaylogics.mvvmnewsapp.R
import com.vinaylogics.mvvmnewsapp.bl.domain.utils.Resource
import com.vinaylogics.mvvmnewsapp.bl.viewmodels.NewsViewModel
import com.vinaylogics.mvvmnewsapp.databinding.FragmentBreakingNewsBinding
import com.vinaylogics.mvvmnewsapp.ui.activities.NewsActivity
import com.vinaylogics.mvvmnewsapp.ui.adapters.ArticleAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [BreakingNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {


    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentBreakingNewsBinding
    private lateinit var articleAdapter: ArticleAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBreakingNewsBinding.bind(view)
        viewModel = (requireActivity() as NewsActivity).viewModel
        setupRecyclerView()

        articleAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_bf_to_af,
                bundle
            )
        }
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
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
        binding.rvBreakingNews.apply {
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

        private  val TAG = BreakingNewsFragment::class.java.simpleName
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ArticleFragment.
         */
        @JvmStatic
        fun newInstance() =
            BreakingNewsFragment()
    }
}