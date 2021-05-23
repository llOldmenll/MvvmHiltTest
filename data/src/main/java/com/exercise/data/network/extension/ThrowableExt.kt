package com.exercise.data.network.extension

import retrofit2.HttpException

fun Throwable.isNotFoundException(): Boolean = this is HttpException && this.code() == 404