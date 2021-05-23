package com.exercise.mvvmhilttest.di

import com.exercise.data.entity.RepositoriesEntity
import com.exercise.data.gateway.RepositoriesGatewayImpl
import com.exercise.data.network.factory.NetworkServiceFactory
import com.exercise.data.network.mapper.RepositoriesEntityToRepositoriesMapper
import com.exercise.data.network.mapper.RepositoryEntityToRepositoryMapper
import com.exercise.data.network.service.RepositoriesNetworkService
import com.exercise.domain.entity.Repositories
import com.exercise.domain.entity.Repository
import com.exercise.domain.gateway.RepositoriesGateway
import com.exercise.domain.mapper.Mapper
import com.exercise.domain.use_case.GetRepositoriesUseCase
import com.exercise.mvvmhilttest.entity.RepositoryDetailsVM
import com.exercise.mvvmhilttest.mapper.RepositoryToRepositoryDetailsVMMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoriesModule {

    @Provides
    @ActivityRetainedScoped
    fun repositoriesMapper(): Mapper<RepositoriesEntity, Repositories> {
        return RepositoriesEntityToRepositoriesMapper(
            RepositoryEntityToRepositoryMapper()
        )
    }

    @Provides
    @ActivityRetainedScoped
    fun repositoriesGateway(
        networkServiceFactory: NetworkServiceFactory,
        repositoriesMapper: Mapper<RepositoriesEntity, Repositories>
    ): RepositoriesGateway = RepositoriesGatewayImpl(
        networkServiceFactory.create(RepositoriesNetworkService::class.java),
        repositoriesMapper
    )

    @Provides
    fun getRepositoriesUseCase(
        repositoriesGateway: RepositoriesGateway
    ): GetRepositoriesUseCase = GetRepositoriesUseCase(repositoriesGateway)

    @Provides
    fun repositoryDetailsMapper(): Mapper<Repository, RepositoryDetailsVM> =
        RepositoryToRepositoryDetailsVMMapper()
}