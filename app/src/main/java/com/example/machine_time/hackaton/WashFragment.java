package com.example.machine_time.hackaton;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.machine_time.hackaton.net.ConnectApi;
import com.example.machine_time.hackaton.net.model.request.WashingScheduleRequest;
import com.example.machine_time.hackaton.net.model.response.AuthUserResponse;
import com.example.machine_time.hackaton.net.model.response.WashingScheduleResponse;

import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;


public class WashFragment extends Fragment implements View.OnClickListener {

    Button timeDialog, dateDialog, showMachine, enroll;
    DialogFragment dlg1, dlg2, dlg3;
    TextView idTimeChoose;
    private Subscription subscription;

    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wash_fragment, container, false);
        getActivity().setTitle("Запись на стирку");

        dlg1 = new DialogTime();
        dlg2 = new DialogDate();
        dlg3 = new DialogMachines();
        timeDialog = (Button)v.findViewById(R.id.timeDialog);
        dateDialog = (Button)v.findViewById(R.id.dateDialog);
        showMachine = (Button)v.findViewById(R.id.showMachine);
        enroll = (Button)v.findViewById(R.id.enroll);
        idTimeChoose = (TextView)v.findViewById(R.id.idTimeChoose);

        enroll.setOnClickListener(this);
        showMachine.setOnClickListener(this);
        timeDialog.setOnClickListener(this);
        dateDialog.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.timeDialog:
                dlg1.show(getActivity().getFragmentManager(), "dlg1");
                break;
            case R.id.dateDialog:
                dlg2.show(getActivity().getFragmentManager(), "dlg2");
                break;
            case R.id.showMachine:
                dlg3.show(getActivity().getFragmentManager(), "dlg3");
                break;
            case R.id.enroll:
                ConnectApi connectApi = ConnectApi.getInstanse();

                sharedPreferences = getActivity().getSharedPreferences("savedData", 0);
                String token = sharedPreferences.getString("token", "");
                String user = sharedPreferences.getString("id", "");
                String time = ((TextView)getActivity().findViewById(R.id.idTimeChoose)).getText().toString();
                String idMachine = ((TextView)getActivity().findViewById(R.id.numberSelectedMachine)).getText().toString();


                WashingScheduleRequest washingScheduleRequest = new WashingScheduleRequest();
                washingScheduleRequest.setDate(dateDialog.getText().toString());
                washingScheduleRequest.setWashingMachine(idMachine);
                washingScheduleRequest.setUser(user);
                washingScheduleRequest.setTime(time);


                subscription = connectApi.enroll("Token " + token, washingScheduleRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(mainThread())
                        .subscribe(new Subscriber<Response<WashingScheduleResponse>>() {
                            @Override
                            public void onCompleted() {
                                Log.d("MainActivity", "onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("MainActivity", "onError => " + e.getMessage());
                            }

                            @Override
                            public void onNext(Response<WashingScheduleResponse> response) {
                                Log.d("MainActivity", "onNext => " + response);
                                if(response.isSuccessful()){
                                    Toast.makeText(getContext(), "Вы записались!", Toast.LENGTH_SHORT).show();
                                    ((ListView)getActivity().findViewById(R.id.lv_time)).setAdapter(null);
                                }else{
                                    Toast.makeText(getContext(), "Произошла ошибка!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            default:
                break;
        }
    }






}
