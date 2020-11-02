package com.les.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeActivity extends AppCompatActivity {
    EditText login, password;
    Button change_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        login = findViewById(R.id.change_login);
        password = findViewById(R.id.change_password);

        Intent intent = getIntent();
        String log =  intent.getStringExtra("username");
        String pas =  intent.getStringExtra("password");

        login.setText(log);
        password.setText(pas);

        change_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}