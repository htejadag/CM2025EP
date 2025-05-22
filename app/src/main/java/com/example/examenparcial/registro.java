package com.example.examenparcial;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent; // Importa Intent
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class registro extends AppCompatActivity {

    EditText etNombre, etCorreo, etCelular, etFechaNacimiento;
    RadioGroup rgGenero;
    CheckBox ckTerminos;
    Button btnRegistrar, btnRegresar, btnValidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Enlazar vistas
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etCelular = findViewById(R.id.etCelular);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        rgGenero = findViewById(R.id.rgGenero);
        ckTerminos = findViewById(R.id.ckTerminos);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnValidar = findViewById(R.id.btnValidar);

        // Habilitar el botón Validar solo si se aceptan los términos
        btnValidar.setEnabled(ckTerminos.isChecked());
        ckTerminos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            btnValidar.setEnabled(isChecked);
        });

        // Seleccionar fecha
        etFechaNacimiento.setOnClickListener(v -> showDatePickerDialog());

        // Acción de registrar
        btnRegistrar.setOnClickListener(view -> registrar());

        // Acción de regresar
        btnRegresar.setOnClickListener(v -> finish());

        // Acción de validar
        btnValidar.setOnClickListener(v -> validarDatos());
    }

    private void registrar() {
        if (validarCampos(true)) {
            String genero = ((RadioButton) findViewById(rgGenero.getCheckedRadioButtonId())).getText().toString();
            showAlert("Éxito", "Registro exitoso. Género seleccionado: " + genero);

            // Pasar a MainActivity después de un registro exitoso
            Intent intent = new Intent(registro.this, MainActivity.class);
            startActivity(intent);  // Esto inicia la nueva actividad
        }
    }

    private void validarDatos() {
        if (!ckTerminos.isChecked()) {
            showAlert("Error", "Debe aceptar los términos y condiciones.");
            return;
        }
        if (validarCampos(false)) {
            showAlert("Éxito", "Validación exitosa. ¡Puedes proceder!");

            // Pasar a MainActivity después de una validación exitosa
            Intent intent = new Intent(registro.this, MainActivity.class);
            startActivity(intent);  // Esto inicia la nueva actividad
        }
    }

    private boolean validarCampos(boolean esRegistro) {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String celular = etCelular.getText().toString().trim();
        String fechaNacimiento = etFechaNacimiento.getText().toString().trim();

        if (nombre.isEmpty() || nombre.length() > 15) {
            showAlert("Error", "Nombre inválido. Debe tener máximo 15 caracteres.");
            return false;
        }

        if (correo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            showAlert("Error", "Correo electrónico inválido.");
            return false;
        }

        if (celular.isEmpty() || celular.length() != 9) {
            showAlert("Error", "Número de celular inválido. Debe tener 9 dígitos.");
            return false;
        }

        if (fechaNacimiento.isEmpty()) {
            showAlert("Error", "Debe ingresar una fecha de nacimiento válida.");
            return false;
        }

        if (rgGenero.getCheckedRadioButtonId() == -1) {
            showAlert("Error", "Debe seleccionar un género.");
            return false;
        }

        if (esRegistro && !ckTerminos.isChecked()) {
            showAlert("Error", "Debe aceptar los términos y condiciones.");
            return false;
        }

        return true;
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    etFechaNacimiento.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
                },
                year, month, day);

        datePickerDialog.show();
    }

    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
