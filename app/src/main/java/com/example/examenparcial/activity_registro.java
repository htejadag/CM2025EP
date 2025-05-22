package com.example.examenparcial;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class activity_registro extends AppCompatActivity {
    EditText etFechaNacimiento, etEmail, etCelular, etNombre;
    Spinner spGenero;
    Button btnValidar, btnRegresar;
    CheckBox chkTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        etFechaNacimiento = findViewById(R.id.etFecha);
        etEmail = findViewById(R.id.etEmail);
        etCelular = findViewById(R.id.etCelular);
        etNombre = findViewById(R.id.txNombre);

        chkTerminos = findViewById(R.id.chkTerminos);

        spGenero = findViewById(R.id.spGenero);

        btnValidar = findViewById(R.id.btnValidar);
        btnRegresar = findViewById(R.id.btnRegresar);

        btnValidar.setEnabled(false);


        // Mostrar DatePickerDialog al hacer clic en EditText
        etFechaNacimiento.setOnClickListener(v -> {
            final Calendar calendario = Calendar.getInstance();
            int anio = calendario.get(Calendar.YEAR);
            int mes = calendario.get(Calendar.MONTH);
            int dia = calendario.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    activity_registro.this,
                    (view, year, month, dayOfMonth) -> {
                        Calendar fechaSeleccionada = Calendar.getInstance();
                        fechaSeleccionada.set(year, month, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        etFechaNacimiento.setText(sdf.format(fechaSeleccionada.getTime()));
                    },
                    anio, mes, dia
            );

            datePickerDialog.show();
        });

        //Cargar elemento al select de genero
        String[] generos = {"Seleccione genero", "Masculino", "Femenino"};
        ArrayAdapter<String> adapterGenero = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, generos
        );
        adapterGenero.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGenero.setAdapter(adapterGenero);

        //boton de validar
        btnValidar.setOnClickListener(v -> {
            String celular = etCelular.getText().toString();
            String email = etEmail.getText().toString();
            String nombre = etNombre.getText().toString();
            String fecha = etFechaNacimiento.getText().toString();


            if (celular.isBlank() || email.isBlank() || nombre.isBlank() || fecha.isBlank() || !esGeneroValido(spGenero)) {
                mostrarAlerta("Rellene todos los campos");
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mostrarAlerta("Ingresa un email valido");
            } else if (celular.length() != 9) {
                mostrarAlerta("Celular debe ser de nueve digitos");
            } else if (!esFechaValida(etFechaNacimiento)) {
                mostrarAlerta("Ingrese una fecha valida");
            } else {
                Toast.makeText(this, "Todo Ok", Toast.LENGTH_SHORT).show();
            }
        });

        chkTerminos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            btnValidar.setEnabled(aceptoTerminosCondiciones(chkTerminos)); // Habilita el botón
        });

        btnRegresar.setOnClickListener(v -> finish());
    }

    private boolean esGeneroValido(Spinner spGenero) {
        return spGenero.getSelectedItemPosition() != 0;
    }

    private boolean aceptoTerminosCondiciones(CheckBox check) {
        return check.isChecked();
    }

    private void mostrarAlerta(String mensaje) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(mensaje)
                .setPositiveButton("OK", null)
                .show();
    }

    private boolean esFechaValida(EditText etFecha) {
        String fechaTexto = etFecha.getText().toString().trim();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sdf.setLenient(false);

        try {
            sdf.parse(fechaTexto);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}