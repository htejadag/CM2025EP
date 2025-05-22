package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Constantes para evitar "magic numbers"
    private static final int MAX_USERNAME_LENGTH = 20;
    private static final int REGISTER_REQUEST_CODE = 1;

    private EditText etUsuario, etContrasena;
    private String registeredEmail = "";
    private String registeredPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupLoginButton();
        setupRegisterLink();
    }

    private void initViews() {
        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        Button btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        TextView tvRegistrarse = findViewById(R.id.tvRegistrarse);
    }

    private void setupLoginButton() {
        findViewById(R.id.btnIniciarSesion).setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString().trim();
            String contrasena = etContrasena.getText().toString().trim();

            if (!validateUsername(usuario)) return;

            if (isValidLogin(usuario, contrasena)) {
                startActivity(new Intent(this, ExitoActivity.class));
            } else {
                showToast(R.string.credenciales_incorrectas);
            }
        });
    }

    private boolean validateUsername(String username) {
        if (username.length() > MAX_USERNAME_LENGTH) {
            showToast(R.string.error_usuario_largo);
            return false;
        }
        return true;
    }

    private boolean isValidLogin(String usuario, String contrasena) {
        String adminUser = getString(R.string.admin_user);
        String adminPass = getString(R.string.admin_password);

        return (usuario.equals(adminUser) && contrasena.equals(adminPass)) ||
                usuario.equals(contrasena) ||
                (usuario.equals(registeredEmail) && contrasena.equals(registeredPassword));
    }

    private void setupRegisterLink() {
        findViewById(R.id.tvRegistrarse).setOnClickListener(v -> {
            startActivityForResult(
                    new Intent(this, RegistroActivity.class),
                    REGISTER_REQUEST_CODE
            );
        });
    }

    private void showToast(int stringResId) {
        Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REGISTER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            registeredEmail = data.getStringExtra("");
            registeredPassword = data.getStringExtra("");

            etUsuario.setText(registeredEmail);
            etContrasena.setText(registeredPassword);

            showToast(R.string.registro_exitoso);
        }
    }
}