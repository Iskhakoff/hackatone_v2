package com.example.machine_time.hackaton;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.machine_time.hackaton.net.model.Machines;
import com.example.machine_time.hackaton.net.model.response.FreeMachineResponse;
import com.example.machine_time.hackaton.net.model.response.WashingMachinesResponse;

import java.util.ArrayList;

public class AdapterMachine extends BaseAdapter{

    Context ctx;
    LayoutInflater layoutInflater;
    ArrayList<Machines> washingMachines = new ArrayList<>();

    public AdapterMachine(Context ctx, ArrayList<Machines> washingMachines) {
        this.ctx = ctx;
        this.washingMachines = washingMachines;
        layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return washingMachines.size();
    }

    @Override
    public Object getItem(int position) {
        return washingMachines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.machine_item, parent, false);
        }

        Machines machines = getMachine(position);

        ((TextView)view.findViewById(R.id.idMachine)).setText(String.valueOf(machines.getId()));
        ((TextView)view.findViewById(R.id.numberMachine)).setText(String.valueOf(machines.getNumber()));


        return view;
    }
    Machines getMachine(int position){
        return((Machines)getItem(position));
    }

}
