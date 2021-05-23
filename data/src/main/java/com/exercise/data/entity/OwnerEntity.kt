package com.exercise.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OwnerEntity(
    @Json(name = "display_name")
    val displayName: String? = null,
    @Json(name = "links")
    val links: LinksEntity? = null
)