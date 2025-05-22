package com.example.examenparcial;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class DatosPersonales extends AppCompatActivity {

    EditText etNombre, etCorreo, etCelular, etFechaNacimiento;
    Spinner spGenero;
    CheckBox chkTerminos;
    Button btnValidar, btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_datos_personales);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etCelular = findViewById(R.id.etCelular);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        spGenero = findViewById(R.id.spGenero);
        chkTerminos = findViewById(R.id.chkTerminos);
        btnValidar = findViewById(R.id.btnValidar);
        btnRegresar = findViewById(R.id.btnRegresar);

        String[] generos = {"Selecciona", "Masculino", "Femenino", "Otro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, generos);
        spGenero.setAdapter(adapter);
        etFechaNacimiento.setOnClickListener(v -> mostrarDatePicker());
        chkTerminos.setOnCheckedChangeListener((buttonView, isChecked) -> btnValidar.setEnabled(isChecked));
        btnValidar.setOnClickListener(v -> validarFormulario());
        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(DatosPersonales.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
    private void mostrarDatePicker() {
        final Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            Calendar fechaSeleccionada = Calendar.getInstance();
            fechaSeleccionada.set(year, month, dayOfMonth);
            Calendar fechaActual = Calendar.getInstance();
            if (fechaSeleccionada.after(fechaActual)) {
                Toast.makeText(DatosPersonales.this, "La fecha no puede ser mayor a la fecha actual.", Toast.LENGTH_SHORT).show();
            } else {
                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                etFechaNacimiento.setText(fecha);
            }
        }, anio, mes, dia);

        datePicker.show();
    }
    private void validarFormulario() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String celular = etCelular.getText().toString().trim();
        String fecha = etFechaNacimiento.getText().toString().trim();
        String genero = spGenero.getSelectedItem().toString();

        if (nombre.isEmpty() || nombre.length() > 15) {
            etNombre.setError("Nombre requerido (máx. 15 caracteres)");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            etCorreo.setError("Correo inválido");
            return;
        }

        if (celular.length() != 9 || !celular.matches("[0-9]+")) {
            etCelular.setError("Celular debe tener 9 dígitos");
            return;
        }

        if (fecha.isEmpty()) {
            etFechaNacimiento.setError("Seleccione una fecha");
            return;
        }

        if (genero.equals("Selecciona")) {
            Toast.makeText(this, "Seleccione un género", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Formulario válido 🎉", Toast.LENGTH_LONG).show();
    }
}