package com.example.rvtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class discrip extends AppCompatActivity {
private ListView LV;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discrip);
        LV=findViewById(R.id.lv);
mRequestQueue= Volley.newRequestQueue(this);
        ArrayList<String> v=new ArrayList<>();
        String city=getIntent().getStringExtra("city");
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"+city+"?unitGroup=us&key=4QBPAD7JTZ4A655LDNERAYHY7&contentType=json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            v.add(response.getString("resolvedAddress"));
                            JSONArray jsonArray = response.getJSONArray("days");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                               v.add(hit.getString("datetime")+" ->  " +
                                       ""+hit.getString("description"));
                            }
                            ArrayAdapter arrayAdapter= new ArrayAdapter(discrip.this, android.R.layout.simple_list_item_1,v);
                            LV.setAdapter(arrayAdapter);

                        } catch (JSONException e) {
                            //  m1.add("error");
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }

}