package com.example.abstraction.datasource

import com.example.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRemoteDataSource {
    fun fetchArticles(query: String?, page: Int): Flow<List<Article>>
}