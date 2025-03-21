package com.example.coursesapp.utils

import android.content.Context
import com.example.coursesapp.model.Course
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object JsonUtils {
    fun loadCoursesFromAssets(context: Context): List<Course> {
        try {
            // Читаем JSON файл из assets
            val jsonString = context.assets.open("courses.json").bufferedReader().use { it.readText() }
            
            // Создаем тип для парсинга JSON
            val courseListType = object : TypeToken<Map<String, List<Course>>>() {}.type
            
            // Парсим JSON
            val coursesMap: Map<String, List<Course>> = Gson().fromJson(jsonString, courseListType)
            
            // Возвращаем список курсов
            return coursesMap["courses"] ?: emptyList()
        } catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }
    }
} 