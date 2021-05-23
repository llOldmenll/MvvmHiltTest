package com.exercise.data.network.di

import com.exercise.data.BuildConfig
import com.exercise.data.network.factory.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun interceptorsFactory(): OkHttpClientFactory = OkHttpClientFactoryImpl()

    @Provides
    @Singleton
    fun converterFactory(): ConverterFactory = ConverterFactoryImpl()

    @Provides
    @Singleton
    fun callAdapterFactory(): CallAdapterFactory = CallAdapterFactoryImpl()

    @Provides
    @Singleton
    fun networkServiceFactory(
        okHttpClientFactory: OkHttpClientFactory,
        converterFactory: ConverterFactory,
        callAdapterFactory: CallAdapterFactory,
    ): NetworkServiceFactory = NetworkServiceFactoryImpl(
        BuildConfig.BASE_URL,
        okHttpClientFactory,
        converterFactory,
        callAdapterFactory
    )
}