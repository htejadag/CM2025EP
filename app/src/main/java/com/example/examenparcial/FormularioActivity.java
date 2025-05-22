package com.example.examenparcial;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class FormularioActivity extends AppCompatActivity {

    EditText editNombre, editCorreo, editCelular, editFecha;
    RadioGroup generoGroup;
    CheckBox checkTerminos;
    Button btnValidar, btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        editNombre = findViewById(R.id.editNombre);
        editCorreo = findViewById(R.id.editCorreo);
        editCelular = findViewById(R.id.editCelular);
        editFecha = findViewById(R.id.editFecha);
        generoGroup = findViewById(R.id.generoGroup);
        checkTerminos = findViewById(R.id.checkTerminos);
        btnValidar = findViewById(R.id.btnValidar);
        btnRegresar = findViewById(R.id.btnRegresar);

        checkTerminos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            btnValidar.setEnabled(isChecked);
        });

        btnValidar.setOnClickListener(v -> {
            if (validarCampos()) {
                mostrarMensaje("Formulario válido y completo");
            }
        });

        btnRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(FormularioActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private boolean validarCampos() {
        String nombre = editNombre.getText().toString().trim();
        String correo = editCorreo.getText().toString().trim();
        String celular = editCelular.getText().toString().trim();
        String fecha = editFecha.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || !nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarMensaje("Nombre inválido. Solo letras y no vacío.");
            return false;
        }

        if (TextUtils.isEmpty(correo) || !android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            mostrarMensaje("Correo electrónico inválido  :(((.");
            return false;
        }

        if (celular.length() != 9 || !celular.matches("[0-9]+")) {
            mostrarMensaje("Número de celular inválido. Debe tener 9 dígitos.");
            return false;
        }

        if (TextUtils.isEmpty(fecha) || !fecha.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            mostrarMensaje("Fecha inválida. Usa el formato dd/mm/aaaa.");
            return false;
        }

        if (generoGroup.getCheckedRadioButtonId() == -1) {
            mostrarMensaje("Selecciona un género.");
            return false;
        }

        return true;
    }

    private void mostrarMensaje(String mensaje) {
        new AlertDialog.Builder(this)
                .setTitle("Validación")
                .setMessage(mensaje)
                .setPositiveButton("OK", null)
                .show();
    }
}
