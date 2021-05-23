package com.exercise.mvvmhilttest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exercise.mvvmhilttest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}