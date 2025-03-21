package com.example.coursesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coursesapp.R
import com.example.coursesapp.databinding.ItemCourseBinding
import com.example.coursesapp.model.Course
import com.example.coursesapp.utils.DateUtils
import java.text.SimpleDateFormat
import java.util.*

class CoursesAdapter : RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {
    private var courses = listOf<Course>()
    private val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))

    class CourseViewHolder(private val binding: ItemCourseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(course: Course, dateFormat: SimpleDateFormat) {
            binding.apply {
                courseTitle.text = course.title
                courseDescription.text = course.text
                coursePrice.text = "${course.price} ₽"
                ratingChip.text = "★ ${course.rate}"
                
                // Форматируем дату начала курса
                val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(course.startDate)
                if (date != null) {
                    startDate.text = dateFormat.format(date)
                }

                // Устанавливаем состояние кнопки избранного
                favoriteButton.setIconResource(
                    if (course.hasLike) R.drawable.ic_bookmark_filled
                    else R.drawable.ic_bookmark
                )

                // Загружаем изображение курса
                val imageResId = root.context.resources.getIdentifier(
                    course.imageResourceName,
                    "drawable",
                    root.context.packageName
                )
                if (imageResId != 0) {
                    courseImage.setImageResource(imageResId)
                }

                // Обработчики нажатий
                favoriteButton.setOnClickListener {
                    course.hasLike = !course.hasLike
                    favoriteButton.setIconResource(
                        if (course.hasLike) R.drawable.ic_bookmark_filled
                        else R.drawable.ic_bookmark
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(
            ItemCourseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position], dateFormat)
    }

    override fun getItemCount() = courses.size

    fun submitList(newCourses: List<Course>) {
        courses = newCourses
        notifyDataSetChanged()
    }
} 