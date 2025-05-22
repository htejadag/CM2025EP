package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
public final static String user="admin";
    public final static String pass="admin";
    public EditText txt_usuario;
public EditText txt_password;
public Button btn_inicio;
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
        txt_password=findViewById(R.id.txtpassword);
        txt_usuario=findViewById(R.id.txtusuario);
        btn_inicio=findViewById(R.id.btninicio);
        btn_inicio.setOnClickListener(v -> {
            String usuarioIngresado = txt_usuario.getText().toString();
            String passwordIngresado = txt_password.getText().toString();

            if (usuarioIngresado.equals(user) && passwordIngresado.equals(pass)) {
                Intent intent = new Intent(MainActivity.this, registropersona.class);
                startActivity(intent);
            } else if (!usuarioIngresado.equals(user) && !passwordIngresado.equals(pass)) {
                Toast.makeText(MainActivity.this, "CREDENCIALES INCORRECTAS", Toast.LENGTH_SHORT).show();
            } else if (!usuarioIngresado.equals(user)) {
                Toast.makeText(MainActivity.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Password incorrecto", Toast.LENGTH_SHORT).show();
            }
        });


    }
}