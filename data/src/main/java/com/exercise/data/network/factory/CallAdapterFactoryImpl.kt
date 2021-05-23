package com.exercise.data.network.factory

import retrofit2.CallAdapter
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class CallAdapterFactoryImpl : CallAdapterFactory {
    override fun create(): CallAdapter.Factory = RxJava3CallAdapterFactory.create()
}