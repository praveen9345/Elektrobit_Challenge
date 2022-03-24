package com.example.electrobitchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.electrobitchallenge.R
import com.example.electrobitchallenge.repository.NewsRepository

class NewsActivity : AppCompatActivity() {
    //lateinit -->used for those variables which are initialized after the declaration
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsRepository= NewsRepository()
        val viewModelProviderFactory= NewsViewModelProviderFactory(application,newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        setContentView(R.layout.activity_news)
    }
}