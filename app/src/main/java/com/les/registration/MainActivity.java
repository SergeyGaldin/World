package com.les.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button login, register;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.login);
        password = findViewById(R.id.password);
        login = findViewById(R.id.come);
        register = findViewById(R.id.registration);

        databaseHelper = new DatabaseHelper(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                if (databaseHelper.isLoginValid(usernameValue, passwordValue)) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                    intent.putExtra("username", usernameValue);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,"Оп Успех!", Toast.LENGTH_SHORT).show();
                } else  {
                    Toast.makeText(MainActivity.this,"Пользователь не найден!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}