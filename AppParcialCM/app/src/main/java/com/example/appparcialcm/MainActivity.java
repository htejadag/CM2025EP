package com.example.appparcialcm;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog; // Import for AlertDialog

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        // Set maximum length for username (20 characters)
        editTextUsername.setFilters(new InputFilter[] { new InputFilter.LengthFilter(20) });

        // Set OnClickListener for the login button
        buttonLogin.setOnClickListener(v -> handleLogin());
    }

    /**
     * Handles the login logic.
     * Validates username and password, then navigates to RegistrationActivity or shows an alert.
     */
    private void handleLogin() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Check username length
        if (username.length() > 20) {
            showAlertDialog(getString(R.string.username_length_alert));
            return;
        }

        // Validate credentials
        if (username.equals(password)) {
            // Credentials are correct, navigate to RegistrationActivity
            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
            startActivity(intent);
            // Optionally, clear fields after successful login
            editTextUsername.setText("");
            editTextPassword.setText("");
        } else {
            // Credentials are incorrect, show an alert
            showAlertDialog(getString(R.string.incorrect_credentials_alert));
        }
    }

    /**
     * Displays a custom AlertDialog with the given message.
     * @param message The message to display in the alert dialog.
     */
    private void showAlertDialog(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
                .show();
    }
}
