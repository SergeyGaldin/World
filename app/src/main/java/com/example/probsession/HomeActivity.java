package com.example.probsession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.probsession.SharedPreferences.SaveToken;

public class HomeActivity extends AppCompatActivity {
    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        logout = findViewById(R.id.imgOut);
    }


    public void logoutClick(View view) {
        SaveToken.save(HomeActivity.this,"token", "false");
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}