package com.example.rvtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        st=String.valueOf((xx*5)/9);
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

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mtext = itemView.findViewById(R.id.exampleText);
            temp = itemView.findViewById(R.id.Dtemp);
            htemp = itemView.findViewById(R.id.Dhigh);
            ltemp = itemView.findViewById(R.id.Dlow);
            wind = itemView.findViewById(R.id.Dwind);
            prec = itemView.findViewById(R.id.Dpreci);
            snow = itemView.findViewById(R.id.Dsnow);
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
