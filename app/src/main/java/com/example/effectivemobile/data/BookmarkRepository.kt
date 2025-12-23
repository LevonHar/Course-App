package com.example.effectivemobile.data

import android.content.Context
import android.content.SharedPreferences
import com.example.effectivemobile.model.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object BookmarkRepository {

    private const val PREFS_NAME = "bookmark_prefs"
    private const val KEY_BOOKMARKS = "bookmarks"

    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    val bookmarks = mutableListOf<Course>()

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadBookmarks()
    }

    private fun loadBookmarks() {
        val json = sharedPreferences.getString(KEY_BOOKMARKS, null)
        if (!json.isNullOrEmpty()) {
            val type = object : TypeToken<MutableList<Course>>() {}.type
            val list: MutableList<Course> = gson.fromJson(json, type)
            bookmarks.clear()
            bookmarks.addAll(list)
        }
    }

    private fun saveBookmarks() {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(bookmarks)
        editor.putString(KEY_BOOKMARKS, json)
        editor.apply()
    }

    fun toggle(course: Course) {
        if (bookmarks.contains(course)) {
            bookmarks.remove(course)
        } else {
            bookmarks.add(course)
        }
        saveBookmarks()
    }

    fun isBookmarked(course: Course): Boolean {
        return bookmarks.contains(course)
    }

    // Optional: manually add a course
    fun add(course: Course) {
        if (!bookmarks.contains(course)) {
            bookmarks.add(course)
            saveBookmarks()
        }
    }
}
