package com.exercise.data.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AvatarEntity(
    @Json(name = "href")
    val href: String? = null
)