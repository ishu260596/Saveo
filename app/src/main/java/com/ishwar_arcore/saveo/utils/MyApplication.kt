package com.ishwar_arcore.saveo.utils

import android.app.Application
import com.ishwar_arcore.saveo.repository.MovieRepository

class MyApplication : Application() {

    val newsRepo by lazy {
        MovieRepository()
    }
}