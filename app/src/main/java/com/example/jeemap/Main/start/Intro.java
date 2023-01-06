package com.example.jeemap.Main.start;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.jeemap.Main.map.MediSelect;
import com.example.jeemap.Main.map.POI;
import com.example.jeemap.R;

public class Intro extends AppCompatActivity {
    POI poi = new POI();
    MediSelect mediSelect= new MediSelect();

    double ulongitude;
    double ulatitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        mypoint();
        getSupportFragmentManager().beginTransaction().replace(R.id.intro_point2, mediSelect).commit();
        //ViewPager vp = findViewById(R.id.viewpager);
        //FragAdater adater = new FragAdater(getSupportFragmentManager());
        //vp.setAdapter(adater);
    }
    public void onFragmentChange(String meditype){
        Bundle bundle = new Bundle();
        bundle.putString("타입",meditype);
        bundle.putString("u위도", Double.toString(ulatitude));
        bundle.putString("u경도", Double.toString(ulongitude));
        poi.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.intro_point2, poi).commit();
    }

    public void mypoint(){
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if ( Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission( getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( Intro.this, new String[] {
                    android.Manifest.permission.ACCESS_FINE_LOCATION}, 0 );
        }
        else {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                ulatitude = location.getLatitude();
                ulongitude = location.getLongitude();
                System.out.println(ulatitude+"="+ ulongitude);
            }
            //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, gps);
            //lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, gps);
        }
    }

    LocationListener gps = new LocationListener() {
        public void onLocationChanged(Location location) {
            ulatitude = location.getLatitude();
            ulongitude = location.getLongitude();
        } public void onStatusChanged(String provider, int status, Bundle extras) {
        } public void onProviderEnabled(String provider) {
        } public void onProviderDisabled(String provider) {
        }
    };
}
