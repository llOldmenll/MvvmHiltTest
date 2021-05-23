package com.exercise.domain.entity

data class Repository(
    val id: String,
    val fullName: String?,
    val language: String?,
    val type: String?,
    val description: String?,
    val createdOn: String?,
    val updatedOn: String?,
    val url: String?,
    val ownerName: String?,
    val ownerAvatar: String?,
    val ownerWebsite: String?
)