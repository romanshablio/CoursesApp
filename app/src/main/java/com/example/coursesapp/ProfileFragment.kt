package com.example.coursesapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coursesapp.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.emailText.text = "Email: ${getCurrentUserEmail()}"
        
        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun getCurrentUserEmail(): String {
        // Получение email текущего пользователя из SharedPreferences
        return context?.getSharedPreferences("auth", Context.MODE_PRIVATE)
            ?.getString("email", "") ?: ""
    }

    private fun logout() {
        context?.getSharedPreferences("auth", Context.MODE_PRIVATE)
            ?.edit()?.clear()?.apply()
        
        // Переход на экран входа
        startActivity(Intent(context, MainActivity::class.java))
        activity?.finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 