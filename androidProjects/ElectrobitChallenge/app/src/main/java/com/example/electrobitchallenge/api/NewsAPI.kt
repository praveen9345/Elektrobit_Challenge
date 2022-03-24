package com.example.electrobitchallenge.api

import com.example.electrobitchallenge.models.NewsResponse
import com.example.electrobitchallenge.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")

    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String ="us",
        @Query("page")
        pageNumber: Int =1, //do not get all the news at once, instead we get 20 article at once
        @Query("apiKey")
        apiKey: String = API_KEY

    //response object generated from that JSON to Kotlin class plugin
    ): Response<NewsResponse>
}