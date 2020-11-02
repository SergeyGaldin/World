package com.les.registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "database";
    static int version = 1;
    static final String TABLE = "users";

    static String createTableUsers = "CREATE TABLE if not exists `users` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `username` TEXT," +
            " `password` TEXT, `email` TEXT, `country` TEXT, `dob` TEXT, `gender` TEXT)";

    public DatabaseHelper( Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableUsers);
    }

    public void insertUser(ContentValues contentValues) {
        getWritableDatabase().insert("users", "",contentValues);
    }

    public boolean isLoginValid(String username, String password) {
        String sql = "Select count(*) from users where username='" + username + "' and password='" + password + "'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        statement.close();

        if (l == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
