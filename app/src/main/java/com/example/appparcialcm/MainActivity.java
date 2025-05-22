package com.example.appparcialcm;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre,  etCelular, etEmail;
    private  EditText etfechaNa;

    public MainActivity(EditText etfechaNa) {
        this.etfechaNa = etfechaNa;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etfechaNa = findViewById(R.id.etFechaNa);
        etCelular = findViewById(R.id.etCelular);
        etEmail = findViewById(R.id.etEmail);
        Button btnButton = findViewById(R.id.btningresar);

        btnSaludar.setOnClickListener(v -> validarYSaludar());
    }

    public void validarYSaludar() {
        String nombre = etNombre.getText().toString().trim();
        String FechaNString  = etfechaNa.getText().toString().trim();
        String celular = etCelular.getText().toString().trim();
        String email = etEmail.getText().toString().trim();

        if(nombre.isEmpty()) {
            etNombre.setError(getString(R.string.error_nombre_obligatorio));
            etNombre.requestFocus();
            return;
        }
        if(nombre.length() > 15) {
            etNombre.setError(getString(R.string.error_max_15_caracteres));
            etNombre.requestFocus();
            return;
        }

        int fechanA;
        try {
            FechaNString = String.valueOf(Integer.parseInt(FechaNString));
        } catch (NumberFormatException e) {
            etfechaNa.setError(getString(R.string.error_edad_invalida));
            etfechaNa.requestFocus();
            return;
        }


        if(celular.length() != 5 || !celular.matches("\\d{5}")) {
            etCelular.setError(getString(R.string.error_celular));
            etCelular.requestFocus();
            return;
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError(getString(R.string.error_email));
            etEmail.requestFocus();
            return;
        }



        String mensaje = String.format(getString(R.string.mensaje_saludo), nombre, criterio);

        Intent intent = new Intent(this, SaludoActivity.class);
        intent.putExtra("mensaje_saludo", mensaje);
        startActivity(intent);


    }



}