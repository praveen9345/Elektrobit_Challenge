package com.example.electrobitchallenge.repository

import com.example.electrobitchallenge.api.RetrofitInstance
import com.example.electrobitchallenge.models.NewsResponse
import com.example.electrobitchallenge.util.Resource
import retrofit2.Response


class NewsRepository() : NewsRepositoryFunction{

    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        return RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)
    }
}