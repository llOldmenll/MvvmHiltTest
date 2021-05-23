package com.exercise.domain.use_case

import com.exercise.domain.entity.Repositories
import com.exercise.domain.gateway.RepositoriesGateway
import com.exercise.domain.use_case.base.SingleUseCase
import io.reactivex.rxjava3.core.Single

class GetRepositoriesUseCase(
    private val repositoriesGateway: RepositoriesGateway
) : SingleUseCase<String, Repositories> {
    override fun execute(data: String?): Single<Repositories> {
        return if (data?.isNotEmpty() == true) repositoriesGateway.getRepositoriesByUrl(data)
        else repositoriesGateway.getRepositories()
    }
}