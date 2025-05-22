package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText etUsuario;
    EditText etContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etUsuario = findViewById(R.id.txtUsuario);
        etContrasena = findViewById(R.id.txtContrasena);
    }

    public void Login(View view){
        String usr = etUsuario.getText().toString();
        String psswd = etContrasena.getText().toString();
        if(usr.compareTo(psswd)==0){
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("usuario",usr);
            i.putExtra("contrasena",psswd);
            startActivity(i);
        }else{
            Toast.makeText(this, "credenciales incorrectas", Toast.LENGTH_LONG).show();
        }

    }
}