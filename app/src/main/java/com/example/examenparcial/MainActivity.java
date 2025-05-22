package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    public EditText eUser;
    public EditText ePass;
    public static final String USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        eUser = findViewById(R.id.txtUsuario);
        ePass = findViewById(R.id.txtPass);
    }

    public void ingresar(View view){
        String usuario = eUser.getText().toString();
        String password = ePass.getText().toString();
        if (esValido(password,usuario)){
            if( usuario.equals(password)){
                Toast.makeText(this,"Bienvenido " + usuario, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this, Inicio.class);
                i.putExtra(USER, usuario);
                startActivity(i);
            }else{
                Snackbar.make(view, "Credenciales incorrectas", Snackbar.LENGTH_SHORT).show();
            }
        }else{
            Snackbar.make(view, "Debes llenar todos los campos", Snackbar.LENGTH_SHORT).show();
        }

    }

    public Boolean esValido(String pass, String user){
        return !TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass);
    }

    public void toRegistro(View view){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }
}