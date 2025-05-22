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
import android.text.InputFilter;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Registro extends AppCompatActivity {

    EditText etNombre, etEmail, etTelefono, etFechaNacimiento;
    Button btnRegresar, btnValidar;
    CheckBox cbTerminosCondiciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etTelefono = findViewById(R.id.etTelefono);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnValidar = findViewById(R.id.btnValidar);
        cbTerminosCondiciones = findViewById(R.id.cbTerminosCondiciones);

        int maxNombreLength = getResources().getInteger(R.integer.max_longitud_nombre);
        etNombre.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxNombreLength) });

        // Configurar el DatePicker
        etFechaNacimiento.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    Registro.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        // Mostrar la fecha seleccionada en el EditText
                        etFechaNacimiento.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });

        btnRegresar.setOnClickListener(v -> finish());

        btnValidar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String email = etEmail.getText().toString();
            String telefono = etTelefono.getText().toString();
            String fechaNacimiento = etFechaNacimiento.getText().toString();
            boolean aceptoTerminos = cbTerminosCondiciones.isChecked();

            if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || fechaNacimiento.isEmpty() || !aceptoTerminos) {
                Toast.makeText(Registro.this, getString(R.string.error_datos_incompletos), Toast.LENGTH_SHORT).show();
            } else {
                if (isValidEmail(email) && telefono.length() <= getResources().getInteger(R.integer.max_longitud_telefono)) {
                    Toast.makeText(Registro.this, getString(R.string.datos_validos), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Registro.this, getString(R.string.error_validacion_datos), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}

