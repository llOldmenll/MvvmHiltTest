package com.exercise.domain.entity

data class Repositories(
    val next: String?,
    val pageLength: Int,
    val list: List<Repository>
)