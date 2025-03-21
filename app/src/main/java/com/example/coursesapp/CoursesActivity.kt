package com.example.coursesapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.coursesapp.adapter.CoursesAdapter
import com.example.coursesapp.databinding.ActivityCoursesBinding
import com.example.coursesapp.model.Course
import com.example.coursesapp.utils.DateConverter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.GsonBuilder

class CoursesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoursesBinding
    private lateinit var coursesAdapter: CoursesAdapter
    private var courses: List<Course> = listOf()
    private var isAscendingOrder = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadCourses()
        setupSortButton()
        setupBottomNavigation()
    }

    private fun setupRecyclerView() {
        coursesAdapter = CoursesAdapter()
        binding.coursesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CoursesActivity)
            adapter = coursesAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@CoursesActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.nav_home -> {
                    showMainContent()
                    true
                }
                R.id.nav_favorites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, FavoritesFragment())
                        .commit()
                    true
                }
                R.id.nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    private fun showMainContent() {
        supportFragmentManager.beginTransaction()
            .remove(supportFragmentManager.findFragmentById(R.id.fragmentContainer) ?: return)
            .commit()
    }

    private fun setupSortButton() {
        binding.sortText.setOnClickListener {
            isAscendingOrder = !isAscendingOrder
            sortCourses()
            updateSortButtonText()
        }
    }

    private fun updateSortButtonText() {
        binding.sortText.text = if (isAscendingOrder) {
            "Сначала старые ⇅"
        } else {
            "Сначала новые ⇅"
        }
    }

    private fun sortCourses() {
        val sortedCourses = if (isAscendingOrder) {
            courses.sortedBy { it.publishDate }
        } else {
            courses.sortedByDescending { it.publishDate }
        }
        
        coursesAdapter.submitList(sortedCourses)
    }

    private fun loadCourses() {
        binding.progressBar.visibility = View.VISIBLE

        try {
            val jsonString = assets.open("courses.json").bufferedReader().use { it.readText() }
            
            val gson = GsonBuilder()
                .registerTypeAdapter(Long::class.java, DateConverter())
                .create()
            
            courses = gson.fromJson(jsonString, Array<Course>::class.java).toList()
            
            Log.d("CoursesActivity", "Loaded courses: ${courses.size}")
            
            sortCourses()
            
            binding.progressBar.visibility = View.GONE
        } catch (e: Exception) {
            Log.e("CoursesActivity", "Error loading courses", e)
            binding.progressBar.visibility = View.GONE
            binding.errorText.apply {
                visibility = View.VISIBLE
                text = "Ошибка загрузки курсов: ${e.message}"
            }
        }
    }
} 