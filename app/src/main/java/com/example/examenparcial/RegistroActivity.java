package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    // Constantes para validaciones
    private static final int PHONE_LENGTH = 9;
    private static final String DATE_PATTERN = "\\d{2}/\\d{2}/\\d{4}";
    private static final String DEFAULT_GENDER = "Seleccione";

    // Declaración de vistas
    private EditText etNombre, etCorreo, etContrasena, etCelular, etFechaNacimiento;
    private Spinner spGenero;
    private CheckBox cbTerminos;
    private Button btnRegistrar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        initViews();
        setupSpinner();
        setupButtons();
    }

    private void initViews() {
        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        etCelular = findViewById(R.id.etCelular);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        spGenero = findViewById(R.id.spGenero);
        cbTerminos = findViewById(R.id.cbTerminos);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegresar = findViewById(R.id.btnRegresar);
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genero_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGenero.setAdapter(adapter);
    }

    private void setupButtons() {
        btnRegistrar.setOnClickListener(v -> validarDatos());
        btnRegresar.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    private void validarDatos() {
        if (!validateFields()) return;

        // Si todas las validaciones pasan
        showToast(R.string.registro_exitoso);

        // Preparar datos para MainActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("correo", etCorreo.getText().toString().trim());
        resultIntent.putExtra("contrasena", etContrasena.getText().toString().trim());
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private boolean validateFields() {
        return validateNombre() &&
                validateCorreo() &&
                validateContrasena() &&
                validateCelular() &&
                validateFechaNacimiento() &&
                validateGenero() &&
                validateTerminos();
    }

    private boolean validateNombre() {
        if (etNombre.getText().toString().trim().isEmpty()) {
            etNombre.setError(getString(R.string.error_nombre));
            etNombre.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateCorreo() {
        String correo = etCorreo.getText().toString().trim();
        if (correo.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            etCorreo.setError(getString(R.string.error_correo));
            etCorreo.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateContrasena() {
        if (etContrasena.getText().toString().trim().isEmpty()) {
            etContrasena.setError(getString(R.string.error_contrasena));
            etContrasena.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateCelular() {
        String celular = etCelular.getText().toString().trim();
        if (celular.isEmpty() || celular.length() != PHONE_LENGTH || !celular.matches("\\d+")) {
            etCelular.setError(getString(R.string.error_celular));
            etCelular.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateFechaNacimiento() {
        String fecha = etFechaNacimiento.getText().toString().trim();
        if (fecha.isEmpty() || !fecha.matches(DATE_PATTERN)) {
            etFechaNacimiento.setError(getString(R.string.error_fecha));
            etFechaNacimiento.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validateGenero() {
        if (spGenero.getSelectedItem().toString().equals(DEFAULT_GENDER)) {
            showToast(R.string.error_genero);
            return false;
        }
        return true;
    }

    private boolean validateTerminos() {
        if (!cbTerminos.isChecked()) {
            showToast(R.string.error_terminos);
            return false;
        }
        return true;
    }

    private void showToast(int stringResId) {
        Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show();
    }
}
