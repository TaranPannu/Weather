package com.example.rvtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class advice extends AppCompatActivity {
private Button bt;
private TextView tv1;
private TextView tvh;
private TextView tvl;
private TextView des;

   private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        bt=findViewById(R.id.btn);
        mRequestQueue = Volley.newRequestQueue(this);
        String city=getIntent().getStringExtra("city");
        tv1=findViewById(R.id.tv1);
        tvl=findViewById(R.id.tvl);
        json(city);
        des=findViewById(R.id.des);
tvh=findViewById(R.id.tvh);
getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(advice.this,discrip.class);
                intent.putExtra("city",city);
                startActivity(intent);

            }
        });
    }
    public void json(String city){
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"+city+"?unitGroup=us&key=4QBPAD7JTZ4A655LDNERAYHY7&contentType=json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int j=0;
                            int temp=0;
                            JSONArray jsonArray = response.getJSONArray("days");
                            int max=0;
                            int min=Integer.MAX_VALUE;
                            String st="";
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                 st=hit.getString("temp");
                             temp=((((Integer.valueOf(st.substring(0,st.indexOf("."))))-32)*5)/9);
if(temp>=20 && temp<=23){
j=i;
break;
}
if(temp<min)
    min=i;
if(temp>max)
    max=i;
                            }
                            if(!(temp>=20)){
                               j=max;
                            }
                            else if(!(temp<=23))
                                j=min;
                            JSONObject hit = jsonArray.getJSONObject(j);
tv1.setText("Best day for going out: "+hit.getString("datetime"));
                            st=hit.getString("feelslike");
tvh.setText("Temp: "+temp+"°C              "+"FeelsLike: "+((((Integer.valueOf(st.substring(0,st.indexOf("."))))-32)*5)/9)+"°C");
                            st=hit.getString("tempmax")+","+hit.getString("tempmin");
tvl.setText("High: "+((((Integer.valueOf(st.substring(0,st.indexOf("."))))-32)*5)/9)+"°C  Low: "+((((Integer.valueOf(st.substring(st.indexOf(",")+1,st.indexOf(".",st.indexOf(",")))))-32)*5)/9)+"°C");
//des.setText(""+hit.getString("description"));
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