package com.les.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView name;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor cursor1;
    public static String fio_in = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.name);
        databaseHelper = new DatabaseHelper(this);

        db = databaseHelper.getReadableDatabase();

        cursor1 = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);


        if (cursor1 != null) {


            cursor1.moveToFirst();

            fio_in=cursor1.getString(1);
            name.setText(fio_in);
        }
        Intent intent = getIntent();
        String user = intent.getStringExtra("username");
        name.setText(user);
    }
}