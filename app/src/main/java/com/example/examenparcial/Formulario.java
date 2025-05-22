package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Formulario extends AppCompatActivity {

    private EditText txtNombre, txtCorreo, txtTelefono, etFechaNacimiento;
    private RadioGroup rgGenero;
    private CheckBox cbAceptarTerminos;
    private Button btnRegresar, btnValidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        // Vincular los elementos con las vistas
        txtNombre = findViewById(R.id.txtNombre);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtTelefono = findViewById(R.id.txtTelefono);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        rgGenero = findViewById(R.id.rgGenero);
        cbAceptarTerminos = findViewById(R.id.cbAceptarTerminos);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnValidar = findViewById(R.id.btnValidar);

        // Evento del botón "Regresar"
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la actividad anterior
                onBackPressed();
            }
        });

        // Evento del botón "Validar"
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validar los datos ingresados
                validarFormulario();
            }
        });
    }

    private void validarFormulario() {
        String nombre = txtNombre.getText().toString().trim();
        String correo = txtCorreo.getText().toString().trim();
        String telefono = txtTelefono.getText().toString().trim();
        String fechaNacimiento = etFechaNacimiento.getText().toString().trim();

        // Validación del nombre (máximo 15 caracteres)
        if (nombre.length() > 15) {
            mostrarAlerta("Error", "El nombre no puede tener más de 15 caracteres.");
            return;
        }

        // Validación del correo electrónico
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            mostrarAlerta("Error", "Correo electrónico inválido.");
            return;
        }

        // Validación del número de teléfono (9 dígitos)
        if (telefono.length() != 9 || !telefono.matches("\\d{9}")) {
            mostrarAlerta("Error", "El número de teléfono debe tener 9 dígitos.");
            return;
        }

        // Validación de la fecha de nacimiento (formato dd/MM/yyyy)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date fecha = sdf.parse(fechaNacimiento);
            if (fecha == null || fecha.after(Calendar.getInstance().getTime())) {
                mostrarAlerta("Error", "Fecha de nacimiento inválida.");
                return;
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Formato de fecha incorrecto.");
            return;
        }

        // Validación de género
        int selectedGenderId = rgGenero.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            mostrarAlerta("Error", "Debe seleccionar un género.");
            return;
        }

        // Validación de aceptación de términos y condiciones
        if (!cbAceptarTerminos.isChecked()) {
            mostrarAlerta("Error", "Debe aceptar los términos y condiciones.");
            return;
        }

        // Si todo es válido, mostrar un mensaje de éxito
        Toast.makeText(Formulario.this, "Formulario válido, datos guardados.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Formulario.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        new AlertDialog.Builder(Formulario.this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("OK", null)
                .show();
    }
}
