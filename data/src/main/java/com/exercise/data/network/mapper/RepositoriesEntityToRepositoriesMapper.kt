package com.exercise.data.network.mapper

import com.exercise.data.entity.RepositoriesEntity
import com.exercise.data.entity.RepositoryEntity
import com.exercise.domain.entity.Repositories
import com.exercise.domain.entity.Repository
import com.exercise.domain.mapper.Mapper

class RepositoriesEntityToRepositoriesMapper(
    private val repositoryMapper: Mapper<RepositoryEntity, Repository>
) : Mapper<RepositoriesEntity, Repositories> {
    override fun map(from: RepositoriesEntity): Repositories = Repositories(
        from.next,
        from.pageLength ?: 0,
        from.repositories?.map { repositoryMapper.map(it) } ?: listOf()
    )
}