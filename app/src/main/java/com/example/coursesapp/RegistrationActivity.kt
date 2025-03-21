package com.example.coursesapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        findViewById<MaterialButton>(R.id.vkButton).setOnClickListener {
            Toast.makeText(this, "VK авторизация будет добавлена позже", Toast.LENGTH_SHORT).show()
        }

        findViewById<MaterialButton>(R.id.okButton).setOnClickListener {
            Toast.makeText(this, "OK авторизация будет добавлена позже", Toast.LENGTH_SHORT).show()
        }
    }
} 