package com.example.rvtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mContext;
    private ArrayList<example_item> mExampleList;
    private static OnItemClickListener mListener1;


    public String FtoC(String t){
        String st="";
        int xx=Integer.valueOf(t.substring(0,t.indexOf(".")));
        //t.charAt(0)+""+t.charAt(1));
        xx=(xx-32);
        st=String.valueOf((xx*5)/9);
        return st;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
      //Intent intent = new Intent(ExampleAdapter.this, DateWise.class);
       // void startActivity(intent);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
     mListener1=  listener;
       // Toast.makeText(mContext, "xxxxx", Toast.LENGTH_SHORT).show();
    }

    public ExampleAdapter(Context context, ArrayList<example_item> exampleList) {
        mContext = context;
        mExampleList =exampleList;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        example_item currentItem = mExampleList.get(position);
        holder.mtext.setText(currentItem.getTv());
        String st = currentItem.getTemp();
      int xx=Integer.valueOf(st.charAt(0)+""+st.charAt(1));
       xx=(xx-32);
        st=String.valueOf((xx*5)/9);
        holder.temp.setText(FtoC(currentItem.getTemp())+"°C");
         st = currentItem.getHigh();
         String t=st.substring(0,st.indexOf(" "));
         st=st.substring(st.indexOf(" ")+1,st.length());
         xx=Integer.valueOf(st.substring(0,st.indexOf(".")));
        xx=(xx-32);
        st=String.valueOf(((xx)*5)/9);
        holder.htemp.setText(t+": "+(st)+"°C");
        st = currentItem.getLow();
         xx=Integer.valueOf(st.substring(0,st.indexOf(".")));
        xx=(xx-32);
        st=String.valueOf((xx*5)/9);


        if(t.equals("FeelsLike"))
            holder.ltemp.setText(" ");
                else
            holder.ltemp.setText("Low "+st+"°C");
        holder.wind.setText("Wind "+currentItem.getWind());
        holder.prec.setText("Preci "+currentItem.getPrec());
        holder.snow.setText("Snow "+currentItem.getSnow());
String i1="https://res.cloudinary.com/dtpgi0zck/image/upload/s--eWjIe4k---/c_fill,h_260,w_380/v1/EducationHub/photos/lightning-bolts.jpg";
    String   i=currentItem.getIcon();
    if(i.equals("wind"))
       i1="https://res.cloudinary.com/dk-find-out/image/upload/q_80,w_1920,f_auto/MA_00050502_cadpti.jpg";
       if(i.equals("rain"))
          i1="https://media.istockphoto.com/photos/transparent-umbrella-under-rain-against-water-drops-splash-background-picture-id1257951336?b=1&k=20&m=1257951336&s=612x612&w=0&h=Va0rZR8NbcpCMfQnA-ogXX4tmQP6SwCk47t8Z6-EQu0=";
if(i.equals("cloudy"))
    i1="https://media.istockphoto.com/id/598222542/photo/sky-background.jpg?s=612x612&w=0&k=20&c=WBAiCExAztT4SzWh4hIgmQwTG7VMJ5o9oObXHszmm8A=";
    if(i.equals("clear-night"))
        i1="https://cdn.fstoppers.com/styles/full/s3/media/2020/12/14/sized_nh20200917-233916-bewerkt-bewerkt-bewerkt.jpg";
     if(i.equals("clear-day"))
         i1="https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/bf5d2cf1-bec5-4040-8dee-2ebe3aec847b/d8oykn-9a011b11-e0dd-4ec3-92b8-057243e7328a.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2JmNWQyY2YxLWJlYzUtNDA0MC04ZGVlLTJlYmUzYWVjODQ3YlwvZDhveWtuLTlhMDExYjExLWUwZGQtNGVjMy05MmI4LTA1NzI0M2U3MzI4YS5qcGcifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6ZmlsZS5kb3dubG9hZCJdfQ.jOUdbKH_QYa8zrhA7V_qZk8WyakPi1J03yrednmuB3M";
      if(i.equals("partly-cloudy-day"))
          i1="https://www.summerlandreview.com/wp-content/uploads/2019/08/17960027_web1_190731-ok-shu-weather-tues.jpg";
      if(i.equals("partly-cloudy-night"))
          i1="https://www.rochesterfirst.com/wp-content/uploads/sites/66/2021/04/cloudy-1869753_1920.jpg?w=900";

      try {
            Picasso.get().load(i1).into(holder.IM);
        }
        catch (Exception e){

        }
    }



    @Override
    public int getItemCount() {
        return mExampleList.size();    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView mtext;
        public TextView temp;
        public TextView htemp;
        public TextView ltemp;
        public TextView wind;
        public TextView prec;
        public TextView snow;
public ImageView IM;
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mtext = itemView.findViewById(R.id.exampleText);
            temp = itemView.findViewById(R.id.Dtemp);
            htemp = itemView.findViewById(R.id.Dhigh);
            ltemp = itemView.findViewById(R.id.Dlow);
            wind = itemView.findViewById(R.id.Dwind);
            prec = itemView.findViewById(R.id.Dpreci);
            snow = itemView.findViewById(R.id.Dsnow);
            IM=itemView.findViewById(R.id.img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener1 != null) {
                        int position = getLayoutPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener1.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
