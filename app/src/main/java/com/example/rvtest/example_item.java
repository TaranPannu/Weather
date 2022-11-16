package com.example.rvtest;

import android.widget.TextView;

public class example_item {
    String icon;
    String tv;
    String temp;
    String high;
    String low;
    String wind;
    String prec;
    String snow;
String city;
    public example_item(String s0,String st,String s1,String s2,String s3,String s4,String s5,String s6,String s7){
        temp=s1;
        tv=st;
        high=s2;
        low=s3;
        wind =s4;
        prec=s5;
        snow=s6;
        city=s0;
        icon=s7;
    }
    public String getTv(){
        return tv;
    }

    public String getIcon() {
        return icon;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getPrec() {
        return prec;
    }

    public String getSnow() {
        return snow;
    }

    public String getTemp() {
        return temp;
    }

    public String getWind() {
        return wind;
    }

    public String getCity() {
        return city;
    }
}
