package com.example.data.datasource

import com.example.abstraction.datasource.NewsRemoteDataSource
import com.example.data.api.response.toModel
import com.example.data.api.service.NewsService
import com.example.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRemoteDataSourceImpl(
    private val newsService: NewsService
) : NewsRemoteDataSource {
    override fun fetchArticles(query: String?, page: Int): Flow<List<Article>> = flow {
        emit(newsService.getArticles(query = query, page = page).results.toModel())
    }

}