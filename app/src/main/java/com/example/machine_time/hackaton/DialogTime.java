package com.example.machine_time.hackaton;

import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.machine_time.hackaton.net.ConnectApi;
import com.example.machine_time.hackaton.net.model.Machines;
import com.example.machine_time.hackaton.net.model.Times;
import com.example.machine_time.hackaton.net.model.response.FreeMachineResponse;
import com.example.machine_time.hackaton.net.model.response.TimeLine;

import java.util.ArrayList;

import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;


public class DialogTime extends DialogFragment{

    ListView lv_time;
    ArrayList<Times> timeWashing = new ArrayList<Times>();
    AdapterTime adapterTime;

    private Subscription subscription;

    SharedPreferences sharedPreferences;
    boolean recap = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_time, container, false);

        lv_time = (ListView)v.findViewById(R.id.lv_time);

        sharedPreferences = getActivity().getSharedPreferences("savedData", 0);
        String token = sharedPreferences.getString("token", "");
        Button button = (Button)getActivity().findViewById(R.id.dateDialog);
        String date = button.getText().toString();
        String dormitory = sharedPreferences.getString("dormitory", "");


        ConnectApi connectApi = ConnectApi.getInstanse();

        subscription = connectApi.getTime("Token " + token, date, dormitory)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .subscribe(new Subscriber<Response<TimeLine>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("MainActivity", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MainActivity", "onError => " + e.getMessage());
                    }

                    @Override
                    public void onNext(Response<TimeLine> response) {
                        Log.d("MainActivity", "onNext => " + response);
                        if(response.isSuccessful()){
                            if(!recap){
                                fillModel(response, response.body().getTimes().size());
                                recap = true;
                            }
                            adapterTime = new AdapterTime(getActivity(), timeWashing);
                            lv_time.setAdapter(adapterTime);
                        }
                    }
                });

        lv_time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String time = timeWashing.get(position).getTime();
                ((TextView)getActivity().findViewById(R.id.idTimeChoose)).setText(String.valueOf(timeWashing.get(position).getId()));
                ((Button)getActivity().findViewById(R.id.timeDialog)).setText(time);
                dismiss();
            }
        });

        return v;

    }

    public void fillModel(Response<TimeLine> timeline, int countItems){
        for (int i = 0; i < countItems; i++) {
            timeWashing.add(new Times(timeline.body().getTimes().get(i).getId(), timeline.body().getTimes().get(i).getTime()));
        }
    }


}
