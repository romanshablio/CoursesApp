package com.example.coursesapp

import android.content.Context
import com.example.coursesapp.model.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoritesManager(context: Context) {
    private val prefs = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun addToFavorites(course: Course) {
        val favorites = getFavorites().toMutableSet()
        favorites.add(course)
        saveFavorites(favorites)
    }

    fun removeFromFavorites(course: Course) {
        val favorites = getFavorites().toMutableSet()
        favorites.removeIf { it.id == course.id }
        saveFavorites(favorites)
    }

    fun isFavorite(courseId: Int): Boolean {
        return getFavorites().any { it.id == courseId }
    }

    fun getFavorites(): Set<Course> {
        val json = prefs.getString("favorite_courses", "[]")
        val type = object : TypeToken<Set<Course>>() {}.type
        return gson.fromJson(json, type) ?: emptySet()
    }

    private fun saveFavorites(favorites: Set<Course>) {
        val json = gson.toJson(favorites)
        prefs.edit().putString("favorite_courses", json).apply()
    }
} 