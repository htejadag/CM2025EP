package com.example.appparcialcm

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appparcialcm.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameInput.text?.toString() ?: ""
            val password = binding.passwordInput.text?.toString() ?: ""

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (username == password) {
                // Login successful, navigate to registration activity
                val intent = Intent(this, RegistrationActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
} 