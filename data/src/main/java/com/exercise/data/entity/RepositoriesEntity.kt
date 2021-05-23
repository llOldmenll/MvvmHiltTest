package com.exercise.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositoriesEntity(
    @Json(name = "next")
    val next: String? = null,
    @Json(name = "pagelen")
    val pageLength: Int? = null,
    @Json(name = "values")
    val repositories: List<RepositoryEntity>? = null
)