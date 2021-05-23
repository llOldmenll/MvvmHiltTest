package com.exercise.data.gateway

import com.exercise.data.entity.RepositoriesEntity
import com.exercise.data.network.service.RepositoriesNetworkService
import com.exercise.domain.entity.Repositories
import com.exercise.domain.gateway.RepositoriesGateway
import com.exercise.domain.mapper.Mapper
import io.reactivex.rxjava3.core.Single

class RepositoriesGatewayImpl(
    private val repositoriesNetworkService: RepositoriesNetworkService,
    private val repositoriesMapper: Mapper<RepositoriesEntity, Repositories>
) : RepositoriesGateway {
    override fun getRepositories(): Single<Repositories> =
        repositoriesNetworkService.getRepositories()
            .map { repositoriesMapper.map(it) }

    override fun getRepositoriesByUrl(url: String): Single<Repositories> =
        repositoriesNetworkService.getRepositoriesByUrl(url)
            .map { repositoriesMapper.map(it) }
}