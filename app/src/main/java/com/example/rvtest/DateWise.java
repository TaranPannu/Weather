package com.example.rvtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.example.rvtest.JSon;
public class DateWise extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {
    private ArrayList<example_item> list;
    private ExampleAdapter mExampleAdapter;
    String city="";
    private RecyclerView mRecyclerView;
    private ArrayList<example_item> list1;
    private ExampleAdapter mExampleAdapter1;
    private RecyclerView mRecyclerView1;
private RequestQueue mQueue;
private String c="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_wise);
        mQueue = Volley.newRequestQueue(this);
        mRecyclerView=findViewById(R.id.rec);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
String s=getIntent().getStringExtra("key");

     //   list1.add(new example_item("s","jj","jj","k","lkj","k","i"));
        list = new ArrayList<>();
         city="St johns";
         if(s.equals("day"))
         {
             //c=getIntent().getStringExtra("city").toString();
             parseJSON(getIntent().getStringExtra("city"));}
                else
                parseJSON1(getIntent().getStringExtra("key"),getIntent().getStringExtra("key1"),getIntent().getStringExtra("key2"));
        list.clear();
    }

    private void parseJSON(String city) {
        final String[] address = {""};
        c=city;
      //  Toast.makeText(this, city+"Date", Toast.LENGTH_SHORT).show();

        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"+city+"?unitGroup=us&key=4QBPAD7JTZ4A655LDNERAYHY7&contentType=json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                             address[0] =response.getString("address");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            JSONArray jsonArray = response.getJSONArray("days");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                list.add(new example_item(address[0],"Date: "+hit.getString("datetime"),hit.getString("temp"),"High "+hit.getString("tempmax"),hit.getString("tempmin"),hit.getString("windspeed"),hit.getString("precip"),hit.getString("snow"),hit.getString("icon")));

                            }

                            mExampleAdapter = new ExampleAdapter(DateWise.this, list);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener( DateWise.this);
                            //  mExampleAdapter.setOnItemClickListener(MainActivity.this);

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
    private void parseJSON1(String city,String date,String p) {
        //Toast.makeText(this, "Date: "+date, Toast.LENGTH_SHORT).show();
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"+city+"?unitGroup=us&key=4QBPAD7JTZ4A655LDNERAYHY7&contentType=json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("days");
//for(int j=0;j<jsonArray.length();j++){
                            JSONObject hit = jsonArray.getJSONObject(Integer.parseInt(p));
                            //if(hit.getString("datetime").equals(date.substring(date.indexOf(" ")+1,date.length()))){
                               // Toast.makeText(MainActivity.this, date+" "+hit.getString("datetime"), Toast.LENGTH_SHORT).show();
                                // list1.add(hit.getString("sunrise"));
                            JSONArray jsonArray1 = hit.getJSONArray("hours");
                            for (int i = 0; i < jsonArray1.length(); i++) {
                                JSONObject hit1 = jsonArray1.getJSONObject(i);
                                // if (hit.getString(""))
                                String st = hit1.getString("temp");
                                list.add(new example_item(city,"Time: "+hit1.getString("datetime"),hit1.getString("temp"),"FeelsLike "+hit.getString("tempmax"),hit.getString("tempmin"),hit1.getString("windspeed"),hit1.getString("precip"),hit1.getString("snow"), hit1.getString("icon")));

                            }//}}
                            mExampleAdapter = new ExampleAdapter(DateWise.this, list);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener( DateWise.this);
                            //  mExampleAdapter.setOnItemClickListener(MainActivity.this);

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
    @Override
    public void onItemClick(int position) {

        example_item clickedItem = list.get(position);
         Toast.makeText(this, clickedItem.getTv(), Toast.LENGTH_SHORT).show();
       Intent intent = new Intent(DateWise.this, DateWise.class);
       intent.putExtra("key",clickedItem.getCity().toString());
        intent.putExtra("key1",clickedItem.getTv().toString());
        intent.putExtra("key2",String.valueOf(position));

        // intent.putExtra("city","Montreal");
        startActivity(intent);
  /*
    list1.add(new example_item("nn","jj","jj","k","lkj","k","i"));
      Toast.makeText(DateWise.this, "hello", Toast.LENGTH_SHORT).show();
        mRecyclerView1=findViewById(R.id.rec1);
        mRecyclerView1.setHasFixedSize(true);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        mExampleAdapter1 = new ExampleAdapter(DateWise.this, list1);
        mRecyclerView1.setAdapter(mExampleAdapter1);*/
    }
}