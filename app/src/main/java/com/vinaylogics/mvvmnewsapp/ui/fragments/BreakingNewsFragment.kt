package com.vinaylogics.mvvmnewsapp.ui.fragments

import androidx.fragment.app.Fragment
import com.vinaylogics.mvvmnewsapp.R



/**
 * A simple [Fragment] subclass.
 * Use the [BreakingNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {




    companion object {
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