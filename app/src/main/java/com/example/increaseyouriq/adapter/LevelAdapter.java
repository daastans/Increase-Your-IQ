package com.example.increaseyouriq.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.increaseyouriq.LevelActivity;
import com.example.increaseyouriq.R;
import com.example.increaseyouriq.database.Question;

import java.util.ArrayList;

public class LevelAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Question> items;


    public LevelAdapter(Context context, ArrayList<Question> levelItems){
        mContext=context;
        items=levelItems;

        Log.e("",items.toString());
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            LayoutInflater layoutInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= layoutInflater.inflate(R.layout.level_item,null);
        }
        TextView levelItemView=view.findViewById(R.id.level_text_view);
        levelItemView.setText(String.valueOf(items.get(i).getQ_no()));
        levelItemView.setBackgroundResource(0);

        Log.e("L",String.valueOf(i)+":"+levelItemView.toString());
        if(items.get(i).getAnswered()==1) {
            levelItemView.setBackgroundResource(R.drawable.btn_gradient);
            Log.e("LADAP","Changed bg of "+levelItemView);

        }
        else{
            levelItemView.setBackgroundResource(R.drawable.bg_grey_radial);
        }
        return view;
    }
}
