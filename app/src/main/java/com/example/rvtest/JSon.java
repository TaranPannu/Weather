package com.example.rvtest;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JSon extends AppCompatActivity {
    private static JSONObject l;
    static String date="x";
    static String discription="..";
    private static ArrayList<String> m=new ArrayList<>();

    protected static ArrayList<String> parseJSON1(RequestQueue mRequestQueue, String city, ArrayList<String> m1) {
        //m=new ArrayList<>();
       // m.clear();
//ArrayList<String> m=m1;
      //  m=new ArrayList<>();
          //String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo";
    //  String url="https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/st%20johns?unitGroup=us&key=4QBPAD7JTZ4A655LDNERAYHY7&contentType=json";
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"+city+"?unitGroup=us&key=4QBPAD7JTZ4A655LDNERAYHY7&contentType=json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("days");
                      //      for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(0);
                           m.add(hit.getString("temp"));
                               m.add(hit.getString("tempmax"));
                                m.add(hit.getString("tempmin"));
                            m.add(hit.getString("description"));
//m.add("yyhh");
                        //       }

                        } catch (JSONException e) {
                            m.add("error");
                            e.printStackTrace();
                        }
                        /*try {
                           // for (int i = 0; i < jsonArray.length(); i++) {
                                       JSONObject hit = jsonArray.getJSONObject(0);
   date=hit.getString("discr");

//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
//JSONObject jb=response.getJSONObject("location");


//return "ff";
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
      //  m.add("hey");
        return m;

    }

}
