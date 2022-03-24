package com.example.electrobitchallenge.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.electrobitchallenge.repository.NewsRepository
import com.example.electrobitchallenge.repository.NewsRepositoryFunction

class NewsViewModelProviderFactory(
    //constructor
    val app: Application,
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app,newsRepository) as T
    }

}