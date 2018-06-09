package com.example.machine_time.hackaton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.machine_time.hackaton.net.model.Machines;
import com.example.machine_time.hackaton.net.model.Times;

import java.util.ArrayList;

public class AdapterTime extends BaseAdapter{

    Context ctx;
    LayoutInflater layoutInflater;
    ArrayList<Times> timeline = new ArrayList<>();

    public AdapterTime(Context ctx, ArrayList<Times> timeline) {
        this.ctx = ctx;
        this.timeline = timeline;
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return timeline.size();
    }

    @Override
    public Object getItem(int position) {
        return timeline.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.time_item, parent, false);
        }

        Times times = getTime(position);

        ((TextView)view.findViewById(R.id.idTime)).setText(String.valueOf(times.getId()));
        ((TextView)view.findViewById(R.id.timeMachine)).setText(times.getTime());

        return view;
    }

    Times getTime(int position){
        return((Times)getItem(position));
    }
}
