package com.example.myapplication

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MainInject {

    @Provides
    fun provideBaseUrl() = BuildConfig.APP_SERVICE_BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor {chain ->
                val request = chain.request().newBuilder().addHeader("X-Api-Key",
                    BuildConfig.APP_SERVICE_API_KEY
                ).build()
                chain.proceed(request)
            }
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }
}