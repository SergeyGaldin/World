package com.les.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    EditText username, password, email, country, dob;
    RadioGroup gender;
    Button register, cancel;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.login);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        country = findViewById(R.id.country);
        dob = findViewById(R.id.dob);
        gender = findViewById(R.id.gender);
        register = findViewById(R.id.reg);
        cancel = findViewById(R.id.cancel);

        databaseHelper = new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                String emailValue = email.getText().toString();
                String countryValue = country.getText().toString();
                String dobValue = dob.getText().toString();
                RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
                String genderValue = checkedBtn.getText().toString();

                if (usernameValue.length() > 1) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("username", usernameValue);
                    contentValues.put("password", passwordValue);
                    contentValues.put("email", emailValue);
                    contentValues.put("country", countryValue);
                    contentValues.put("dob", dobValue);
                    contentValues.put("gender", genderValue);

                    databaseHelper.insertUser(contentValues);
                    Toast.makeText(RegistrationActivity.this, "Успех!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    intent.putExtra("username", usernameValue);
                    intent.putExtra("password", passwordValue);
                    intent.putExtra("email", emailValue);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegistrationActivity.this, "Провал!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}