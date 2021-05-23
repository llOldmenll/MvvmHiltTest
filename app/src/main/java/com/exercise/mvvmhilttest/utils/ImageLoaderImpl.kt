package com.exercise.mvvmhilttest.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.exercise.mvvmhilttest.R

class ImageLoaderImpl : ImageLoader {
    override fun load(context: Context, url: String, view: ImageView) {
        Glide.with(context)
            .load(url)
            .centerInside()
            .placeholder(R.drawable.ic_placeholder)
            .into(view)
    }

    override fun clear(context: Context, view: View) =
        Glide.with(context).clear(view)
}