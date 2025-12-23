package com.example.effectivemobile.model

import android.app.Application
import com.example.effectivemobile.data.BookmarkRepository

data class Course(
    val id: Long,              // идентификатор курса
    val title: String,          // заголовок курса
    val text: String,           // описание курса
    val price: String,          // цена курса
    val rate: Float,            // рейтинг курса
    val startDate: String,      // дата начала курса
    val publishDate: String,    // дата публикации курса
    val hasLike: Boolean        // добавлен ли в избранное
)
