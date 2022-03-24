package com.example.electrobitchallenge.ui

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.electrobitchallenge.repository.FakeNewsRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    private lateinit var viewModel: NewsViewModel
    @Before
    fun setup(){
         val app= ApplicationProvider.getApplicationContext<Application>()
          viewModel= NewsViewModel(app,FakeNewsRepository())
    }
    @Test
    fun insertCountryCodeEmpty_returns(){
        val value= viewModel.getBreakingNews("de")
        assertThat(value).isNotNull()
    }




}
