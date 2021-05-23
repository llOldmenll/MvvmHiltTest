package com.exercise.data.network.mapper

import com.exercise.data.entity.RepositoryEntity
import com.exercise.domain.entity.Repository
import com.exercise.domain.mapper.Mapper
import com.exercise.domain.utils.toFormattedDate

class RepositoryEntityToRepositoryMapper : Mapper<RepositoryEntity, Repository> {
    override fun map(from: RepositoryEntity): Repository {
        return Repository(
            from.uuid ?: "",
            from.fullName,
            from.language,
            from.type,
            from.description,
            from.createdOn?.toFormattedDate(),
            from.updatedOn?.toFormattedDate(),
            from.links?.html?.href,
            from.owner?.displayName,
            from.owner?.links?.avatar?.href,
            from.website
        )
    }
}