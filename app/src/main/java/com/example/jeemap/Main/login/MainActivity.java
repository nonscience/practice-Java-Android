package com.example.jeemap.Main.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jeemap.R;

public class MainActivity extends AppCompatActivity {

    private TextView tv_id, tv_pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        tv_id = findViewById(R.id.tv_id);
        tv_pass = findViewById(R.id.tv_pass);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPass = intent.getStringExtra("userPass");

        tv_id.setText(userID);
        tv_pass.setText(userPass);
    }
}
