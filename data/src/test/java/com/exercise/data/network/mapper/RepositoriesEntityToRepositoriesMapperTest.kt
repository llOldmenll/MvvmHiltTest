package com.exercise.data.network.mapper

import com.exercise.data.entity.RepositoriesEntity
import com.exercise.data.entity.RepositoryEntity
import com.exercise.data.fakeRepositoriesEntity
import com.exercise.domain.entity.Repositories
import com.exercise.domain.entity.Repository
import com.exercise.domain.mapper.Mapper
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class RepositoriesEntityToRepositoriesMapperTest {

    private lateinit var repositoryMapper: Mapper<RepositoryEntity, Repository>
    private lateinit var repositoriesMapper: Mapper<RepositoriesEntity, Repositories>

    @Before
    fun setUp() {
        repositoryMapper = RepositoryEntityToRepositoryMapper()
        repositoriesMapper = RepositoriesEntityToRepositoriesMapper(repositoryMapper)
    }

    @Test
    fun mapRepositoriesEntityToRepositoriesCorrectly() {
        // Arrange
        val expected = Repositories(
            fakeRepositoriesEntity.next,
            fakeRepositoriesEntity.pageLength ?: 0,
            (fakeRepositoriesEntity.repositories ?: listOf()).map { repositoryMapper.map(it) }
        )

        // Act
        val result = repositoriesMapper.map(fakeRepositoriesEntity)

        // Assert
        TestCase.assertEquals(result, expected)
    }
}