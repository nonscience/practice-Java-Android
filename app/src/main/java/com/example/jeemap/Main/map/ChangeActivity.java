package com.example.jeemap.Main.map;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jeemap.R;

public class ChangeActivity extends AppCompatActivity {
    MediMap mediMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mediMap = new MediMap();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        String latitude = getIntent().getStringExtra("위도");
        String longitude = getIntent().getStringExtra("경도");
        String ulatitude = getIntent().getStringExtra("u위도");
        String ulongitude = getIntent().getStringExtra("u경도");

        Bundle bundle = new Bundle();
        bundle.putString("위도", latitude);
        bundle.putString("경도", longitude);
        bundle.putString("u위도", ulatitude);
        bundle.putString("u경도", ulongitude);

        mediMap.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.intro_point2, mediMap).commit();
    }
}
