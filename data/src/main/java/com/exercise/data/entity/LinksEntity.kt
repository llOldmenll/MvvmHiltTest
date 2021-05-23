package com.exercise.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinksEntity(
    @Json(name = "html")
    val html: HtmlEntity? = null,
    @Json(name = "avatar")
    val avatar: AvatarEntity? = null
)