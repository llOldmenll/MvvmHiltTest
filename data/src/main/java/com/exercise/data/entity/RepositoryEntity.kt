package com.exercise.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositoryEntity(
    @Json(name = "uuid")
    val uuid: String? = null,
    @Json(name = "full_name")
    val fullName: String? = null,
    @Json(name = "language")
    val language: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "created_on")
    val createdOn: String? = null,
    @Json(name = "updated_on")
    val updatedOn: String? = null,
    @Json(name = "website")
    val website: String? = null,
    @Json(name = "links")
    val links: LinksEntity? = null,
    @Json(name = "owner")
    val owner: OwnerEntity? = null,
)