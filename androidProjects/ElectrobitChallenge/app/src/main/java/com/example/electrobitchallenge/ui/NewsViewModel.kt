package com.example.electrobitchallenge.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electrobitchallenge.NewsApplication
import com.example.electrobitchallenge.models.NewsResponse
import com.example.electrobitchallenge.repository.NewsRepository
import com.example.electrobitchallenge.repository.NewsRepositoryFunction
import com.example.electrobitchallenge.util.Constants.Companion.COUNTRY_CODE
import com.example.electrobitchallenge.util.Resource
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response


class NewsViewModel(
    /*
     * constructor
     * Note: by default constructor arg can not be allowed. for this we need that than
     *  crate viewModelProviderFactory(to define how our viewModel should be created)
     */
    app: Application,
    val newsRepository: NewsRepositoryFunction
) : AndroidViewModel(app) {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData() //Live Data

    //manage the pagination here instead in fragment to prevent the data reset during screen orientation
    var brakingNewsPage = 1

    var breakingNewsResponse: NewsResponse? = null

    init { //invoke automatically
        getBreakingNews(COUNTRY_CODE)
    }

    // In repository getBreakingNews is a suspend function i.e means call the another suspend fun or coroutine( in this coroutine is used)
    //And we don't want to run from the fragment therefore vieModelScope(it is alive until viewModel is alive)is used to run it from viewModel
    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        safeBreakingNewsCall(countryCode)
    }


    private suspend fun safeBreakingNewsCall(countryCode: String) {
        breakingNews.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = newsRepository.getBreakingNews(countryCode, brakingNewsPage)
                breakingNews.postValue(handleBreakingNewsResponse(response)) ////Livedata post
            } else {
                breakingNews.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> breakingNews.postValue(Resource.Error("Network Failure"))
                else -> breakingNews.postValue(Resource.Error("Conversion Error"))
            }
        }
    }
    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                brakingNewsPage++
                if (breakingNewsResponse == null) {
                    breakingNewsResponse = resultResponse
                } else {
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun hasInternetConnection(): Boolean {
        //connectivityManager is system manager which need context and this cannot call inside the viewModel but we can solve the problem using application context
        val connectivityManager = getApplication<NewsApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}