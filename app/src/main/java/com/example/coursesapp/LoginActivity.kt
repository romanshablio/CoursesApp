package com.example.coursesapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<ImageButton>(R.id.vkButton).setOnClickListener {
            Toast.makeText(this, "VK авторизация будет добавлена позже", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageButton>(R.id.okButton).setOnClickListener {
            Toast.makeText(this, "OK авторизация будет добавлена позже", Toast.LENGTH_SHORT).show()
        }

        findViewById<TextView>(R.id.forgotPasswordLink).setOnClickListener {
            Toast.makeText(this, "Восстановление пароля будет добавлено позже", Toast.LENGTH_SHORT).show()
        }

        findViewById<TextView>(R.id.registrationLink).setOnClickListener {
            Toast.makeText(this, "Регистрация будет добавлена позже", Toast.LENGTH_SHORT).show()
        }

        // Добавляем проверку пароля
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<MaterialButton>(R.id.loginButton)
        
        passwordInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                if ((s?.length ?: 0) < 8) {
                    passwordInput.error = "Минимум 8 символов"
                    loginButton.isEnabled = false
                } else {
                    passwordInput.error = null
                    loginButton.isEnabled = true
                }
            }
        })

        val emailInput = findViewById<EditText>(R.id.emailInput)
        
        // Функция для проверки email
        fun isEmailValid(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        // Проверяем email при изменении текста
        emailInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                val email = s?.toString() ?: ""
                if (!isEmailValid(email)) {
                    emailInput.error = "Неверный формат email"
                } else {
                    emailInput.error = null
                }
            }
        })

        // Тестовые учетные данные
        val TEST_EMAIL = "test@test.com"
        val TEST_PASSWORD = "12345678"

        // Обработчик нажатия кнопки входа
        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email == TEST_EMAIL && password == TEST_PASSWORD) {
                val intent = Intent(this, CoursesActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Показываем ошибку при неверных данных
                Toast.makeText(
                    this,
                    "Неверный email или пароль",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
} 