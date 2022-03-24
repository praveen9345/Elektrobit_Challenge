package com.example.electrobitchallenge.models

import java.io.Serializable

//it is not a primitive data type(like int, float)-->it is complex data type-->therefore mark this class as Serializable
//Serializable--> able to pass this class between several fragments
data class Article(
    //make all properties nullable because if absence of a property in article leads to appcrashes
    val author: String?=null,
    val content: String?=null,
    val description: String?=null,
    val publishedAt: String?=null,
    val source: Source?=null,
    val title: String?=null,
    val url: String?=null,
    val urlToImage: String?=null
): Serializable