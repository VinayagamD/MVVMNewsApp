package com.vinaylogics.mvvmnewsapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vinaylogics.mvvmnewsapp.R
import com.vinaylogics.mvvmnewsapp.bl.viewmodels.NewsViewModel
import com.vinaylogics.mvvmnewsapp.databinding.FragmentSavedNewsBinding
import com.vinaylogics.mvvmnewsapp.ui.activities.NewsActivity
import com.vinaylogics.mvvmnewsapp.ui.adapters.ArticleAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [SavedNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {

    private lateinit var binding: FragmentSavedNewsBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var articleAdapter: ArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavedNewsBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel
        setUpRecyclerView()
        articleAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_search_nf_to_af,
                bundle
            )
        }
    }

    private fun setUpRecyclerView() {
        articleAdapter = ArticleAdapter()
        binding.rvSavedNews.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment BreakingNewsFragment.
         */
        @JvmStatic
        fun newInstance() =
            SavedNewsFragment()
    }
}