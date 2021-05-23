package com.exercise.mvvmhilttest.utils

import android.content.Context
import android.view.View
import android.widget.ImageView

interface ImageLoader {
    fun load(context: Context, url: String, view: ImageView)
    fun clear(context: Context, view: View)
}