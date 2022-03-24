package com.example.electrobitchallenge.models

import com.example.electrobitchallenge.models.Article

data class NewsResponse(
    val articles: MutableList<Article>, //MutableList --> allow to concatenation of two list
    val status: String,
    val totalResults: Int
)