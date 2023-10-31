package com.example.data.repository

import com.example.abstraction.datasource.NewsRemoteDataSource
import com.example.abstraction.repository.NewsRepository
import com.example.model.Article
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val remote: NewsRemoteDataSource,
) : NewsRepository {
    override fun fetchArticles(query: String?, page: Int): Flow<List<Article>> {
        return remote.fetchArticles(query, page)
    }

}