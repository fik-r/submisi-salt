package com.example.data.di

import com.example.abstraction.datasource.NewsRemoteDataSource
import com.example.abstraction.repository.NewsRepository
import com.example.data.api.service.NewsService
import com.example.data.datasource.NewsRemoteDataSourceImpl
import com.example.data.repository.NewsRepositoryImpl
import com.example.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataInject {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideNewsService(retrofit: Retrofit) = retrofit.create(NewsService::class.java)

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        newsService: NewsService
    ): NewsRemoteDataSource = NewsRemoteDataSourceImpl(
        newsService
    )

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource
    ): NewsRepository = NewsRepositoryImpl(
        newsRemoteDataSource
    )

    @Singleton
    @Provides
    fun provideGetArticles(
        newsRepository: NewsRepository
    ) = GetArticlesUseCase(newsRepository)

}