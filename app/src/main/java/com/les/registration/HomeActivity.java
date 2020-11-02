package com.les.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView name;
    Button change;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.name);
        change = findViewById(R.id.change);
        databaseHelper = new DatabaseHelper(this);

        db = databaseHelper.getReadableDatabase();
        cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);

        Intent intent = getIntent();
        String user = intent.getStringExtra("username");
        String pas = intent.getStringExtra("password");
        name.setText(user);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ChangeActivity.class);
                intent.putExtra("username", user);
                intent.putExtra("password", pas);
                startActivity(intent);
            }
        });
    }
}