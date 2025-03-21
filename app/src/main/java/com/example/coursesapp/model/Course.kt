package com.example.coursesapp.model

import com.google.gson.annotations.SerializedName
import com.example.coursesapp.utils.DateConverter

data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val description: String = text, // для обратной совместимости
    val imageResourceName: String? = null,
    val price: String = "",
    val rate: String = "0.0",
    val rating: Double = rate.toDoubleOrNull() ?: 0.0,
    val startDate: String = "",
    @SerializedName("publishDate")
    @com.google.gson.annotations.JsonAdapter(DateConverter::class)
    val publishDate: Long = 0,
    var hasLike: Boolean = false
) 