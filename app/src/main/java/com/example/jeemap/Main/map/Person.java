package com.example.jeemap.Main.map;

public class Person {
    String MainText;
    String SubText;
    String selNum;
    String latitude;
    String longitude;
    public Person(String MainText, String SubText, String selNum, String latitude, String longitude) {
        this.MainText = MainText;
        this.SubText = SubText;
        this.selNum = selNum;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public String getMainText() {
        return MainText;
    }
    public String getlatitude() {
        return latitude;
    }
    public String getlongitude() {
        return longitude;
    }
}