package com.example.jeemap.Main.map;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.jeemap.Main.start.Intro;
import com.example.jeemap.R;

public class MediSelect extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mediselect, container, false);
        Button menu1 = view.findViewById(R.id.menu1);
        Button menu2 = view.findViewById(R.id.menu2);
        Button menu3 = view.findViewById(R.id.menu3);
        Button menu4 = view.findViewById(R.id.menu4);
        Intent intent = new Intent(getActivity(), POI.class);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Intro)getActivity()).onFragmentChange("의원");
            }
        });
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("증상", "보건");
                startActivity(intent);
            }
        });
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("증상", "기타");
                startActivity(intent);
            }
        });
        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("증상", "약국");
                startActivity(intent);
            }
        });
        return view;
    }
}
