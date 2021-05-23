package com.exercise.data.network.mapper

import com.exercise.data.entity.RepositoryEntity
import com.exercise.data.fakeRepositoryEntity
import com.exercise.domain.entity.Repository
import com.exercise.domain.mapper.Mapper
import com.exercise.domain.utils.toFormattedDate
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class RepositoryEntityToRepositoryMapperTest {

    private lateinit var repositoryMapper: Mapper<RepositoryEntity, Repository>

    @Before
    fun setUp() {
        repositoryMapper = RepositoryEntityToRepositoryMapper()
    }

    @Test
    fun mapRepositoryEntityToRepositoryCorrectly() {
        // Arrange
        val expected = Repository(
            fakeRepositoryEntity.uuid ?: "",
            fakeRepositoryEntity.fullName,
            fakeRepositoryEntity.language,
            fakeRepositoryEntity.type,
            fakeRepositoryEntity.description,
            fakeRepositoryEntity.createdOn?.toFormattedDate(),
            fakeRepositoryEntity.updatedOn?.toFormattedDate(),
            fakeRepositoryEntity.links?.html?.href,
            fakeRepositoryEntity.owner?.displayName,
            fakeRepositoryEntity.owner?.links?.avatar?.href,
            fakeRepositoryEntity.website
        )

        // Act
        val result = repositoryMapper.map(fakeRepositoryEntity)

        // Assert
        assertEquals(result, expected)
    }
}