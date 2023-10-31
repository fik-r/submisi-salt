package com.example.usecase

import com.example.abstraction.repository.NewsRepository
import com.example.common.UseCase
import com.example.model.Article
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class GetArticlesUseCase(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<List<Article>, GetArticlesUseCase.Params>() {
    override fun build(params: Params?): Flow<List<Article>> {
        requireNotNull(params)
        return newsRepository.fetchArticles(params.query, params.page).flowOn(dispatcher)
    }

    data class Params(
        val query: String?,
        val page: Int
    )
}