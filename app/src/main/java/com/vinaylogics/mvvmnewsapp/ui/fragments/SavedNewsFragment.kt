package com.vinaylogics.mvvmnewsapp.ui.fragments

import androidx.fragment.app.Fragment
import com.vinaylogics.mvvmnewsapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [SavedNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavedNewsFragment : Fragment(R.layout.fragment_saved_news) {


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