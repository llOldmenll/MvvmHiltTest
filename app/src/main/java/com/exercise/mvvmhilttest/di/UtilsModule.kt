package com.exercise.mvvmhilttest.di

import com.exercise.mvvmhilttest.utils.ImageLoader
import com.exercise.mvvmhilttest.utils.ImageLoaderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object UtilsModule {

    @Provides
    @FragmentScoped
    fun imageLoader(): ImageLoader = ImageLoaderImpl()
}