package com.example.jeemap.Main.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.jeemap.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class POI extends Fragment {
    Button button1;
    TextView textNum;
    PersonAdapter adapter = new PersonAdapter();

    String Symptom;
    String ulatitude;
    String ulongitude;
    String ApiKey = "l7xxa94c7f5e4edc482182f70499dfc71c66";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_poi, container, false);
        Context ct = container.getContext();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ct, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        textNum = view.findViewById(R.id.textNum);
        button1 = view.findViewById(R.id.findmedi);

        Symptom = getArguments().getString("타입");
        ulatitude = getArguments().getString("u위도");
        ulongitude = getArguments().getString("u경도");

        pashing();
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textNum.setText(adapter.getItemCount() + "개");
                adapter.userPoint(ulatitude, ulongitude);
                adapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    void pashing(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request poi_request = new Request.Builder()
                        .url("https://apis.openapi.sk.com/tmap/pois?version=1&searchKeyword="
                                + "의료시설" + "&searchType=all&searchtypCd=R&centerLon="
                                + ulongitude + "&centerLat="
                                + ulatitude + "&reqCoordType=WGS84GEO&resCoordType=WGS84GEO&radius=5&page=1&count=20&multiPoint=N&poiGroupYn=N")
                        .get()
                        .addHeader("Accept", "application/json")
                        .addHeader("appKey", ApiKey)
                        .build();
                Response poi_response = null;
                try {
                    poi_response = client.newCall(poi_request).execute();
                } catch (IOException e) {
                    System.out.println(" null ");
                    return;
                }
                String poi_message = null;
                try {
                    poi_message = poi_response.body().string();
                } catch (IOException e) {
                    System.out.println(" null ");
                    return;
                }
                System.out.println("파싱 메시지 : " + poi_message);
                String poi_finalMessage = poi_message;
                try {
                    JSONParse_POI(poi_finalMessage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
             }
            }).start();
        }

    private void JSONParse_POI(String finalMessage) throws JSONException {

        JSONObject original_JSON;
        original_JSON = new JSONObject(finalMessage); //전체 배열 가져오기 중복 없음!

        JSONObject searchPoiInfo = original_JSON.optJSONObject("searchPoiInfo"); // 전체 배열 중 resultdata 아래 있는 데이터 가져오기) 중복 없음!
        JSONObject pois = searchPoiInfo.optJSONObject("pois"); // 전체 배열 중 resultdata 아래 있는 데이터 가져오기) 중복 없음!
        JSONArray poi = pois.optJSONArray("poi"); // 전체 배열 다음 다음 배열
        if (poi == null) { // null 값 검증
            System.out.println("0값 발생음");
            return;
        }
        String name[] = new String[poi.length()];
        String upperAddrName[] = new String[poi.length()];
        String middleAddrName[] = new String[poi.length()];
        String lowerAddrName[] = new String[poi.length()];
        String totalAddrName[] = new String[poi.length()];
        String frontLat[] = new String[poi.length()];
        String frontLon[] = new String[poi.length()];
        String check_poi[] = new String[poi.length()];
        String middleBizName[] = new String[poi.length()];
        String lowerBizName[] = new String[poi.length()];

        for (int i = 0; i < poi.length(); i++) {    //여기서 포문 한번 더 돌려서 의료시설 아닌곳 거르기
           JSONObject array_json = poi.optJSONObject(i); // 배열로했던 장소중 I 의 배열을 참조
           middleBizName[i] = array_json.getString("middleBizName");
           lowerBizName[i] = array_json.getString("lowerBizName");
            if(lowerBizName[i].contains(Symptom)){
               name[i] = array_json.optString("name", "."); //좌표값 찾기
               upperAddrName[i] = array_json.optString("upperAddrName", "."); //좌표값 찾기
               middleAddrName[i] = array_json.optString("middleAddrName", "."); //좌표값 찾기
               lowerAddrName[i] = array_json.optString("lowerAddrName", "."); //좌표값 찾기
               frontLat[i] = array_json.optString("frontLat", "0");
               frontLon[i] = array_json.optString("frontLon", "0");
               check_poi[i] = Integer.toString(i);
               totalAddrName[i] = (upperAddrName[i] + " " + middleAddrName[i] + " " + lowerAddrName[i]);
               String MainText = name[i];
               String SubText = totalAddrName[i];
               String selNum = check_poi[i];
               String latitude = frontLat[i];
               String longitude = frontLon[i];

               System.out.println(name[i] + " "+ totalAddrName[i] + " " + totalAddrName[i] +" "+ check_poi[i]+""+ totalAddrName[i]);

               adapter.addItem(new Person(MainText, SubText, selNum, latitude, longitude));
            }
        }
    }
}




