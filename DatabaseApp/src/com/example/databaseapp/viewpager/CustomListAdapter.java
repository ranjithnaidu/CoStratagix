package com.example.databaseapp.viewpager;

import java.util.List;

import com.example.databaseapp.model.Response;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Response> responses;
    
    public CustomListAdapter(Activity activity, List<Response> responses) {
        this.activity = activity;
        this.responses = responses;
    }
 
    @Override
    public int getCount() {
        return responses.size();
    }
 
    @Override
    public Object getItem(int location) {
        return responses.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
 
        TextView text = (TextView) convertView.findViewById(android.R.id.text1);
       
        Response m = responses.get(position);
         
        text.setText(m.getDateTime());
 
        return convertView;
    }
 
}