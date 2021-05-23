package com.exercise.data.network.service

import com.exercise.data.entity.RepositoriesEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface RepositoriesNetworkService {

    @GET("repositories")
    fun getRepositories(): Single<RepositoriesEntity>

    @GET
    fun getRepositoriesByUrl(@Url url: String): Single<RepositoriesEntity>
}