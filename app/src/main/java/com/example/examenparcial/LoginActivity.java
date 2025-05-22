package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsuario, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Limitar usuario a 20 caracteres
        etUsuario.setFilters(new InputFilter[] {new InputFilter.LengthFilter(20)});

        // Ocultar contraseña
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = etUsuario.getText().toString().trim();
                String password = etPassword.getText().toString();

                if (usuario.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, getString(R.string.msg_campos_vacios), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (usuario.equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, getString(R.string.msg_credenciales_incorrectas), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
