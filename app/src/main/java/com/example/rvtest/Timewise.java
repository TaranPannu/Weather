package com.example.rvtest;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


        public class Timewise extends AppCompatActivity {
            private RequestQueue mQueue;
private ListView lv;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_timewise);
                mQueue = Volley.newRequestQueue(this);
                lv=findViewById(R.id.LV);
                ArrayList<String> v = new ArrayList<>();
                v.add("Time: 00:00                                                            5 C        Feels like: 4C");
                v.add("hell         o");
                v.add("hey");
                //Intent intent = getIntent();
                String city = getIntent().getStringExtra("city");
                v.add(city+"m12m");
                parseJSON(city,v);

                //ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, v);
              //  lv.setAdapter(arrayAdapter);
            }
            private void parseJSON(String city,ArrayList<String >v) {
                String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"+city+"?unitGroup=us&key=4QBPAD7JTZ4A655LDNERAYHY7&contentType=json";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("days");
                                       JSONObject hit = jsonArray.getJSONObject(0);
                                       v.add(hit.getString("sunrise"));
                                    JSONArray jsonArray1 = hit.getJSONArray("hours");
                                    for (int i = 0; i < jsonArray1.length(); i++) {
                                        JSONObject hit1 = jsonArray1.getJSONObject(i);
                                       // if (hit.getString(""))
                                        String st = hit1.getString("temp");
                                        //+" "+hit1.getString("feelslike");
                                        String temp="";
                                        for(int i1=0;i1<st.length();i1++){
                                           // temp+=st.charAt(i);
                                        }
                                        int xx=Integer.valueOf(st.charAt(0)+st.charAt(1));
                                        xx=(xx-32);
                                        temp=String.valueOf((xx*5)/9);
                                    //   st=String.valueOf(((Integer.valueOf(st.charAt(2)+""+st.charAt(3))-32)*5)/9);
                                        v.add("Time: "+hit1.getString("datetime")+"                                                    "+temp+"°C        Feels like: "+hit1.getString("feelslike"));
                                    //    v.add();
                                    }

                                    ArrayAdapter arrayAdapter = new ArrayAdapter(Timewise.this, android.R.layout.simple_list_item_1, v);
                                    lv.setAdapter(arrayAdapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                mQueue.add(request);
            }

        }

