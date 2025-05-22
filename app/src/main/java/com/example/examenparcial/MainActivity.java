package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText etUser, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        int maxUserLength = getResources().getInteger(R.integer.max_longitud_usuario);
        etUser.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxUserLength) });

        btnLogin.setOnClickListener(v -> {
            String user = etUser.getText().toString();
            String password = etPassword.getText().toString();

            if (user.equals(password)) {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            } else {
                Toast.makeText(
                        MainActivity.this,
                        getString(R.string.login_error_msg),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}