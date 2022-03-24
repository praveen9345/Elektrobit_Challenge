package com.example.electrobitchallenge.api

import com.example.electrobitchallenge.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Retrofit singleton class-->that enable to make request from everywhere in our code
class RetrofitInstance {
    companion object {
        //lazy--> means initialize only once
        private val retrofit by lazy{
            val logging = HttpLoggingInterceptor() //log response of retrofit-->useful for debugging
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            //now pass the client to retrofit instance
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                //determine how the response should be interpreted and converted to Kotlin object--> GsonConverterFactory
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
        }

        //create the instance from Retrofit builder
        val api by lazy {
            //arg--> class of the API interface
            retrofit.create(NewsAPI::class.java)
        }

    }
}