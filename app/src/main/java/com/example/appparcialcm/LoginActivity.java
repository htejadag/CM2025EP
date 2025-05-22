package com.example.appparcialcm;

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

    EditText et1;
    EditText et2;


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

        et1 = findViewById(R.id.txtUsuario);
        et2 = findViewById(R.id.txtPassword);


    }

    public void Login(View view) {


        String usuario = et1.getText().toString();
        String password = et2.getText().toString();


        if (usuario.equals(password)) {

            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("usuario", usuario);
            i.putExtra("password", password);
            startActivity(i);

        }
        else{
            Toast.makeText(this, "CREDENCIALES INCORRECTAS", Toast.LENGTH_SHORT).show();
        }


    }
}