package com.example.examenparcial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.examenparcial.Entities.Person;
import com.example.examenparcial.Entities.User;

public class Login extends AppCompatActivity {

    EditText etl1, etl2;

    //genero un usuario predeterminado
    User user = new User("dc", "123");

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
        etl1 = findViewById(R.id.etl1);
        etl2 = findViewById(R.id.etl2);

    }

    public void iniciarSesion(View view){
        String userTemp = etl1.getText().toString();
        String passtemp = etl2.getText().toString();
        if(validar(userTemp, passtemp)){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("usuario", userTemp);
            startActivity(intent);
        }
    }

    public boolean validar(String u, String p){
        return (u.equals(user.getUser()) && p.equals(user.getPass()));
    }

}