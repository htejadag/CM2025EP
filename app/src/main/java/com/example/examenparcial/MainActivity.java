package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculando los elementos de la interfaz con las variables
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Agregar el TextWatcher para el campo de usuario (etEmail)
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // No se necesita hacer nada antes de que cambie el texto
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Verifica si el texto tiene más de 20 caracteres
                if (charSequence.length() > 20) {
                    // Si tiene más de 20 caracteres, corta el texto a 20
                    etEmail.setText(charSequence.subSequence(0, 20));
                    etEmail.setSelection(20); // Mantiene el cursor al final del texto
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No se necesita hacer nada después de que el texto haya cambiado
            }
        });

        // Evento de clic para el botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                // Validación de longitud para el usuario (máximo 20 caracteres)
                if (usuario.length() > 20) {
                    Toast.makeText(MainActivity.this, "El usuario no puede tener más de 20 caracteres", Toast.LENGTH_SHORT).show();
                    return;  // Detener la ejecución si el usuario tiene más de 20 caracteres
                }

                // Validación de las credenciales (usuario y contraseña)
                if (usuario.equals("admin") && password.equals("admin")) {
                    // Si los datos son correctos
                    Toast.makeText(MainActivity.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                } else {
                    // Si los datos son incorrectos
                    Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Botón de registro
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Formulario.class);
            startActivity(intent);
        });
    }
}
