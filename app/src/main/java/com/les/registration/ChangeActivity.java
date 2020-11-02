package com.les.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangeActivity extends AppCompatActivity {
    EditText login, password;
    Button change_but, delete_but;

    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        login = findViewById(R.id.change_login);
        password = findViewById(R.id.change_password);
        change_but = findViewById(R.id.change_but);
        delete_but = findViewById(R.id.delete_but);

        change_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();

        Intent intent = getIntent();
        String log =  intent.getStringExtra("username");
        String pas =  intent.getStringExtra("password");

        login.setText(log);
        password.setText(pas);
        delete_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(DatabaseHelper.TABLE, "username = ?", new String[] {log});
                Toast.makeText(ChangeActivity.this, "Пользователь удален!", Toast.LENGTH_SHORT).show();
                Intent intentDel = new Intent(ChangeActivity.this, MainActivity.class);
                startActivity(intentDel);
                db.close();
            }
        });
    }

}