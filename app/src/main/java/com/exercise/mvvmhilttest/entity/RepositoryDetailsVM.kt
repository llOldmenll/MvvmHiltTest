package com.exercise.mvvmhilttest.entity

data class RepositoryDetailsVM(
    val name: String,
    val avatar: String,
    val list: List<RepositoryInfoVM>
)
