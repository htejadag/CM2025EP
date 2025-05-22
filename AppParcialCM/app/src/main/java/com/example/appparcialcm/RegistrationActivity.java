package com.example.appparcialcm;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPhone;
    private EditText editTextDob; // Date of Birth
    private RadioGroup radioGroupGender;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private CheckBox checkBoxTerms;
    private Button buttonValidate;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize UI components
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextDob = findViewById(R.id.editTextDob);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        buttonValidate = findViewById(R.id.buttonValidate);
        buttonBack = findViewById(R.id.buttonBack);

        // Set maximum length for name (15 characters)
        editTextName.setFilters(new InputFilter[] { new InputFilter.LengthFilter(15) });

        // Set OnClickListener for Date of Birth field to show DatePickerDialog
        editTextDob.setOnClickListener(v -> showDatePickerDialog());

        // Enable/disable validate button based on terms and conditions checkbox
        checkBoxTerms.setOnCheckedChangeListener((buttonView, isChecked) -> buttonValidate.setEnabled(isChecked));


        // Set OnClickListener for validate button
        buttonValidate.setOnClickListener(v -> validateRegistration());

        // Set OnClickListener for back button
        buttonBack.setOnClickListener(v -> finish()); // Go back to the previous activity (MainActivity)
    }

    /**
     * Displays a DatePickerDialog to select the date of birth.
     */
    private void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Format the date as YYYY-MM-DD
                    String formattedMonth = String.format(new Locale("es", "PE"), "%02d", selectedMonth + 1);
                    String formattedDay = String.format(new Locale("es", "PE"), "%02d", selectedDay);
                    String selectedDate = selectedYear + "-" + formattedMonth + "-" + formattedDay;
                    editTextDob.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    /**
     * Validates all fields in the registration form.
     * Shows an AlertDialog if any validation fails, otherwise shows a success message.
     */
    private void validateRegistration() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String dob = editTextDob.getText().toString().trim();
        int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
        boolean termsAccepted = checkBoxTerms.isChecked();

        // 1. Validate terms and conditions
        if (!termsAccepted) {
            showAlertDialog(getString(R.string.terms_not_accepted_alert));
            return;
        }

        // 2. Validate all fields are filled
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(dob) || selectedGenderId == -1) {
            showAlertDialog(getString(R.string.complete_all_fields_alert));
            return;
        }

        // 3. Validate name length (max 15 characters)
        if (name.length() > 15) {
            showAlertDialog(getString(R.string.name_length_alert));
            return;
        }

        // 4. Validate email format
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showAlertDialog(getString(R.string.invalid_email_alert));
            return;
        }

        // 5. Validate phone number (exactly 9 digits)
        if (!Pattern.matches("^\\d{9}$", phone)) {
            showAlertDialog(getString(R.string.invalid_phone_alert));
            return;
        }

        // 6. Validate date of birth format (YYYY-MM-DD)
        if (!isValidDate(dob)) {
            showAlertDialog(getString(R.string.invalid_dob_alert));
            return;
        }

        // If all validations pass
        showAlertDialog(getString(R.string.registration_success_alert));
        // Here you would typically save the data or navigate to another screen
    }

    /**
     * Checks if a date string is in YYYY-MM-DD format and is a valid date.
     * @param dateString The date string to validate.
     * @return True if the date string is valid, false otherwise.
     */
    private boolean isValidDate(String dateString) {
        // Regex to check YYYY-MM-DD format
        if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", dateString)) {
            return false;
        }
        try {
            // Attempt to parse the date to check if it's a real date
            // This will catch invalid dates like 2023-02-30
            String[] parts = dateString.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            Calendar cal = Calendar.getInstance();
            cal.setLenient(false); // Make Calendar strict about date parsing
            cal.set(year, month - 1, day); // Month is 0-indexed in Calendar

            // If any part of the date is invalid, set() will throw an IllegalArgumentException
            cal.getTime();
            return true;
        } catch (Exception e) {
            return false;
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
