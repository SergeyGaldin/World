package com.les.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        login = findViewById(R.id.change_login);
        password = findViewById(R.id.change_password);
        change_but = findViewById(R.id.change_but);
        delete_but = findViewById(R.id.delete_but);

        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        String log =  intent.getStringExtra("username");
        String pas =  intent.getStringExtra("password");
        login.setText(log);
        password.setText(pas);

        change_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = databaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.COLUMN_USERNAME, login.getText().toString());
                contentValues.put(DatabaseHelper.COLUMN_PASSWORD, password.getText().toString());

                db.update(DatabaseHelper.TABLE, contentValues,"username = ?", new String[] {log});
                db.update(DatabaseHelper.TABLE, contentValues,"password = ?", new String[] {pas});

                String login_save = login.getText().toString();
                String password_save = password.getText().toString();
                Toast.makeText(ChangeActivity.this, "Данные сохранены", Toast.LENGTH_SHORT).show();

                Intent intentSave = new Intent(ChangeActivity.this, HomeActivity.class);
                intentSave.putExtra("username", login_save);
                intentSave.putExtra("password", password_save);
                startActivity(intentSave);
                db.close();
            }
        });

        delete_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = databaseHelper.getWritableDatabase();
                db.delete(DatabaseHelper.TABLE, "username = ?", new String[] {log});
                Toast.makeText(ChangeActivity.this, "Пользователь удален!", Toast.LENGTH_SHORT).show();
                Intent intentDel = new Intent(ChangeActivity.this, MainActivity.class);
                startActivity(intentDel);
                db.close();
            }
        });
    }

}