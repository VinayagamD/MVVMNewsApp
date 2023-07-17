package com.vinaylogics.mvvmnewsapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.vinaylogics.mvvmnewsapp.R
import com.vinaylogics.mvvmnewsapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fcvNewsNavHost) as NavHostFragment

        navHostFragment.navController
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bnvNews.setupWithNavController(navController)



    }


}