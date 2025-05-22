package com.example.examenparcial;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class registro extends AppCompatActivity {

    EditText etNombre, etCorreo, etCelular, etFechaNacimiento;
    CheckBox checkTerminos;
    Button btnVolverLogin, btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etCelular = findViewById(R.id.etCelular);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        checkTerminos = findViewById(R.id.checkTerminos);
        btnVolverLogin = findViewById(R.id.btnVolverLogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setEnabled(false); // El botón empieza deshabilitado
        checkTerminos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            btnRegistrar.setEnabled(isChecked); // Solo se activa si se marca el checkbox
        });

        // ➕ Selección de fecha con DatePickerDialog
        etFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendario = Calendar.getInstance();
                int anio = calendario.get(Calendar.YEAR);
                int mes = calendario.get(Calendar.MONTH);
                int dia = calendario.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog selectorFecha = new DatePickerDialog(registro.this,
                        (view, year, month, dayOfMonth) -> {
                            String fechaSeleccionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                            etFechaNacimiento.setText(fechaSeleccionada);
                        }, anio, mes, dia);
                selectorFecha.show();
            }
        });

        btnVolverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Volver a la pantalla de login
                Intent intent = new Intent(registro.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDatos();
            }
        });
    }

    private boolean validarEdadMinima(String fecha, int edadMinima) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date fechaNacimiento = formato.parse(fecha);

            Calendar fechaMinima = Calendar.getInstance();
            fechaMinima.add(Calendar.YEAR, -edadMinima); // fecha límite para la edad mínima

            return fechaNacimiento.before(fechaMinima.getTime());
        } catch (ParseException e) {
            return false; // Si falla el formato de fecha
        }
    }


    private void validarDatos() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String celular = etCelular.getText().toString().trim();
        String fecha = etFechaNacimiento.getText().toString().trim();
        boolean terminosAceptados = checkTerminos.isChecked();

        if (nombre.isEmpty() || correo.isEmpty() || celular.isEmpty() || fecha.isEmpty()) {
            mostrarAlerta("Por favor, completa todos los campos.");
        } else if (!correo.contains("@") || !correo.contains(".")) {
            mostrarAlerta("Correo electrónico no válido.");
        } else if (celular.length() < 9 || !celular.matches("[0-9]+")) {
            mostrarAlerta("Número de celular inválido.");
        } else if (!terminosAceptados) {
            mostrarAlerta("Debes aceptar los términos y condiciones.");
        } else if (!validarEdadMinima(fecha, 18)) {
            mostrarAlerta("Debes tener al menos 15 años.");
        } else {
            mostrarAlerta("Registro exitoso ✅");
            // Aquí puedes continuar o guardar los datos
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