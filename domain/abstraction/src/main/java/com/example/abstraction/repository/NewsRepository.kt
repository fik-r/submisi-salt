package com.example.abstraction.repository

import com.example.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun fetchArticles(query: String?, page: Int): Flow<List<Article>>
}