package com.dicoding.asclepius.data.remote.retrofit

import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.data.remote.response.NewsResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("top-headlines")
    fun getNews(
        @Query("q") topic: String = "cancer",
        @Query("category") category: String = "health",
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = apiToken
    ): Call<NewsResponse>

    companion object {
        private const val apiToken = BuildConfig.API_TOKEN
    }

}