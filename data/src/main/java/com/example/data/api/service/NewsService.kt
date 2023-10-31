package com.example.data.api.service

import com.example.data.api.response.ResponseArticles
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines")
    suspend fun getArticles(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("q") query: String? = null,
        @Query("country") country: String = "us"
    ): ResponseArticles
}