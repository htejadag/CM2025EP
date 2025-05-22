package com.example.examenparcial;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Registro extends AppCompatActivity {

        EditText etNombre, etCorreo, etCelular, etFechaNacimiento;
        RadioGroup rgGenero;
        CheckBox cbTerminos;
        Button btnValidar, btnRegresar;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registro);

            etNombre = findViewById(R.id.etNombre);
            etCorreo = findViewById(R.id.etCorreo);
            etCelular = findViewById(R.id.etCelular);
            etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
            rgGenero = findViewById(R.id.rgGenero);
            cbTerminos = findViewById(R.id.cbTerminos);
            btnValidar = findViewById(R.id.btnValidar);
            btnRegresar = findViewById(R.id.btnRegresar);

            // Selección de fecha con DatePicker
            etFechaNacimiento.setOnClickListener(v -> showDatePicker());

            // Activar botón validar solo si se aceptan los términos
            cbTerminos.setOnCheckedChangeListener((buttonView, isChecked) -> btnValidar.setEnabled(isChecked));

            btnValidar.setOnClickListener(v -> validarDatos());

            btnRegresar.setOnClickListener(v -> finish()); // Vuelve a MainActivity
        }

        private void showDatePicker() {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                etFechaNacimiento.setText(fecha);
            },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        }

        private void validarDatos() {
            String nombre = etNombre.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String celular = etCelular.getText().toString().trim();
            String fecha = etFechaNacimiento.getText().toString().trim();
            int generoId = rgGenero.getCheckedRadioButtonId();

            if (nombre.isEmpty() || nombre.length() > 15) {
                mostrarAlerta("Nombre inválido (máx 15 caracteres).");
            } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                mostrarAlerta("Correo electrónico inválido.");
            } else if (!celular.matches("\\d{9}")) {
                mostrarAlerta("Número de celular debe tener 9 dígitos.");
            } else if (fecha.isEmpty()) {
                mostrarAlerta("Debe ingresar una fecha de nacimiento.");
            } else if (generoId == -1) {
                mostrarAlerta("Debe seleccionar un género.");
            } else {
                mostrarAlerta("Todos los datos son válidos.");
            }
        }

        private void mostrarAlerta(String mensaje) {
            new AlertDialog.Builder(this)
                    .setTitle("Validación")
                    .setMessage(mensaje)
                    .setPositiveButton("OK", null)
                    .show();
        }
    }


