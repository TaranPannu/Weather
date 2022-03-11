package com.example.rvtest;
import com.example.rvtest.JSon;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {
   // private static AbstractCollection<JsonObjectRequest> mRequestQueue;
    private RequestQueue mRequestQueue;
    private Button bt;
    private TextView Dis;
    private TextView Cur;
    private TextView High;
    private TextView Low;
    private EditText ED;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRequestQueue = Volley.newRequestQueue(this);
        Dis=findViewById(R.id.text1);
        Cur=findViewById(R.id.textc);
        High=findViewById(R.id.texth);
        Low=findViewById(R.id.textl);
        bt=findViewById(R.id.btn);
ED=findViewById(R.id.edit1);
        bt.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View view) {
        String s=ED.getText().toString()+"";
       parseJSON(s);
        return false;
    }
});
bt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(MainActivity.this, "City: "+ED.getText(), Toast.LENGTH_SHORT).show();
       // String s=ED.getText().toString()+"";
        Intent intent = new Intent(MainActivity.this, DateWise.class);
        intent.putExtra("city",ED.getText().toString()+"");
        intent.putExtra("key","day");
        startActivity(intent);

}});
    }
    public String FtoC(String t){
        String st="";
        int xx=Integer.valueOf(t.substring(0,t.indexOf(".")));//t.charAt(0)+""+t.charAt(1));
        xx=(xx-32);
        st=String.valueOf((xx*5)/9);
        return st;
    }

    public void parseJSON(String city) {
    //    Toast.makeText(this, "inside", Toast.LENGTH_SHORT).show();
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
                            Cur.setText((FtoC(hit.getString("temp")))+"°C");
                            High.setText("High:"+FtoC(hit.getString("tempmax"))+"°C");
                            Low.setText("Low:"+FtoC(hit.getString("tempmin"))+"°C");
                            Dis.setText((hit.getString("description")));

                            //m1.add(hit.getString("tempmax"));
                            //m1.add(hit.getString("tempmin"));
                            //m1.add(hit.getString("description"));
//m.add("yyhh");
                            //       }

                        } catch (JSONException e) {
                          //  m1.add("error");
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

    }
    }

