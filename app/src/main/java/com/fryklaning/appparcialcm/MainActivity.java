package com.fryklaning.appparcialcm;

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

public class MainActivity extends AppCompatActivity {

    public EditText etUsuario;
    public EditText etContra;



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

        etUsuario = findViewById(R.id.txtUsuario);
        etContra = findViewById(R.id.txtContra);

    }

    public void Ingresar(View view){

        String Usuario = etUsuario.getText().toString();
        String Contra = etContra.getText().toString();

        if(Usuario.length()>20){
            Toast.makeText(this, "Movimiento invalido: Usuario maximo 20 carecteres", Toast.LENGTH_SHORT).show();
        }else{
            if(Usuario.equals(Contra)){
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this,MainActivity2.class);
                startActivity(i);

            }else{
                Toast.makeText(this, "Usuario y contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        }

    }
}