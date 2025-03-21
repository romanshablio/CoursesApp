package com.example.coursesapp

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

class MainActivity : AppCompatActivity() {
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.continueButton).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Находим все элементы курсов и устанавливаем слушатель
        val courseItems = findViewById<ViewGroup>(R.id.coursesContainer)
            .touchables.filter { it is TextView }
        
        courseItems.forEach { view ->
            view.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // При нажатии
                        v.isPressed = true  // Добавляем это для активации selector
                        val rotateAnimation = ObjectAnimator.ofFloat(v, "rotation", 0f, -30f)
                        rotateAnimation.duration = 300
                        rotateAnimation.start()
                        true
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        // При отпускании или отмене
                        v.isPressed = false  // Возвращаем в нормальное состояние
                        v.animate().rotation(0f).setDuration(300).start()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun showError(message: String) {
        errorText.text = message
        errorText.visibility = View.VISIBLE
        
        // Создаем анимацию затухания
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.duration = 300
        
        // Создаем анимацию исчезновения
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.duration = 300
        fadeOut.startOffset = 2000 // Задержка перед исчезновением
        
        // Устанавливаем слушатель для анимации исчезновения
        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                errorText.visibility = View.GONE
            }
        })
        
        // Запускаем анимации
        errorText.startAnimation(fadeIn)
        errorText.startAnimation(fadeOut)
    }
} 