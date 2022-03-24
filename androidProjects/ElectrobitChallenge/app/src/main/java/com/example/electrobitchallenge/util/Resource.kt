package com.example.electrobitchallenge.util

//to rap around our network responses--> use ful to differentiated between successful, error and loading status
//sealed class--> like abstract class but able to define which class can inherit from Resource class
sealed class Resource<T>(
    //constructor
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String,data: T?=null): Resource<T>(data,message)
    class Loading<T>:Resource<T>()
}