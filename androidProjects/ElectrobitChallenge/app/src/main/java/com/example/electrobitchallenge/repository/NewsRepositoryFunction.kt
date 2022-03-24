package com.example.electrobitchallenge.repository

import com.example.electrobitchallenge.models.NewsResponse
import com.example.electrobitchallenge.util.Resource
import retrofit2.Response

// solving the problem of test double
interface NewsRepositoryFunction {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int):Response<NewsResponse>
}