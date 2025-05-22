package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tvm1, tvm2;

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
    }

    public void registrar(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void mostrar(){
        tvm1 = findViewById(R.id.tvm1);
        String user = getIntent().getStringExtra("usuario");
        tvm1.setText("BIENVENIDO USUARIO: "+user);

        tvm2 = findViewById(R.id.tvm2);
        tvm2.setText("PERSONAS REGISTRADAS");
    }
}