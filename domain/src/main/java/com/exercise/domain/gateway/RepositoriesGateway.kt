package com.exercise.domain.gateway

import com.exercise.domain.entity.Repositories
import io.reactivex.rxjava3.core.Single

interface RepositoriesGateway {

    fun getRepositories(): Single<Repositories>

    fun getRepositoriesByUrl(url: String): Single<Repositories>
}