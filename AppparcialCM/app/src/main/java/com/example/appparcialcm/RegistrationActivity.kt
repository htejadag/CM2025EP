package com.example.appparcialcm

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appparcialcm.databinding.ActivityRegistrationBinding
import java.text.SimpleDateFormat
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup date picker
        setupDatePicker()

        // Setup terms checkbox listener
        binding.termsCheckbox.setOnCheckedChangeListener { _, isChecked ->
            binding.validateButton.isEnabled = isChecked
        }

        // Setup validate button
        binding.validateButton.setOnClickListener {
            validateForm()
        }
    }

    private fun setupDatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            updateDateInView()
        }

        binding.birthdateInput.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateDateInView() {
        val format = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        binding.birthdateInput.setText(sdf.format(calendar.time))
    }

    private fun validateForm() {
        var isValid = true
        var errorMessage = ""

        // Validate name
        val name = binding.nameInput.text?.toString() ?: ""
        if (name.isBlank()) {
            errorMessage = "Por favor ingrese su nombre"
            isValid = false
        }

        // Validate email
        val email = binding.emailInput.text?.toString() ?: ""
        if (email.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "Por favor ingrese un correo electrónico válido"
            isValid = false
        }

        // Validate phone
        val phone = binding.phoneInput.text?.toString() ?: ""
        if (phone.length != 9 || !phone.all { it.isDigit() }) {
            errorMessage = "Por favor ingrese un número de celular válido de 9 dígitos"
            isValid = false
        }

        // Validate birthdate
        val birthdate = binding.birthdateInput.text?.toString() ?: ""
        if (birthdate.isBlank()) {
            errorMessage = "Por favor seleccione su fecha de nacimiento"
            isValid = false
        }

        // Validate gender
        if (binding.genderGroup.checkedRadioButtonId == -1) {
            errorMessage = "Por favor seleccione su género"
            isValid = false
        }

        if (isValid) {
            Toast.makeText(this, "Formulario validado correctamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
} 