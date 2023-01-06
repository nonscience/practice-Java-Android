package com.example.jeemap.Main.map;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.jeemap.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

public class MediMap extends Fragment implements TMapGpsManager.onLocationChangedCallback, View.OnClickListener {
    double latitude;
    double longitude;
    double ulatitude;
    double ulongitude;
    String ApiKey = "l7xxa94c7f5e4edc482182f70499dfc71c66";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_map, container, false);
        Context ct = container.getContext();
        latitude = Double.parseDouble(getArguments().getString("위도"));
        longitude = Double.parseDouble(getArguments().getString("경도"));
        ulatitude = Double.parseDouble(getArguments().getString("u위도"));
        ulongitude = Double.parseDouble(getArguments().getString("u경도"));
        initMap(view, ct);
        return view;
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onLocationChange(Location location) {
    }


    void initMap(View view , Context ct){
        LinearLayout linearLayoutTmap = view.findViewById(R.id.mapview);
        TMapView tMapView = new TMapView(ct);
        tMapView.setSKTMapApiKey(ApiKey);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setLocationPoint(ulongitude,  ulatitude);
        tMapView.setCenterPoint(ulongitude,  ulatitude);
        linearLayoutTmap.addView( tMapView );
        addMoving(tMapView);
    }


    void addMoving(TMapView tMapView){
        TMapPoint tMapPointStart  = new TMapPoint(ulatitude, ulongitude);
        TMapPoint tMapPointEnd  = new TMapPoint(latitude, longitude);
        TMapMarkerItem marker1 = new TMapMarkerItem();
        TMapMarkerItem marker2 = new TMapMarkerItem();
        marker1.setTMapPoint(tMapPointStart);
        marker2.setTMapPoint(tMapPointEnd);
        tMapView.addMarkerItem("marker1",marker1);
        tMapView.addMarkerItem("marker2",marker2);

        new Thread(() -> {
            try {
                TMapPolyLine tMapPolyLine = new TMapData().findPathData(tMapPointStart, tMapPointEnd);
                tMapPolyLine.setLineColor(Color.BLUE);
                tMapPolyLine.setLineWidth(5);
                tMapView.addTMapPolyLine("Line1", tMapPolyLine);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
