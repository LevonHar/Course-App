package com.example.effectivemobile.app

import android.app.Application
import com.example.effectivemobile.data.BookmarkRepository

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        BookmarkRepository.init(this)
    }
}