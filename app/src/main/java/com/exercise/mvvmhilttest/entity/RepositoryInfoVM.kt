package com.exercise.mvvmhilttest.entity

import androidx.annotation.StringRes

data class RepositoryInfoVM(
    @StringRes
    val title: Int,
    val description: String
)
