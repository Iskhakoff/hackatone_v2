package com.example.machine_time.hackaton;

import android.app.DialogFragment;
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
import com.example.machine_time.hackaton.net.model.response.FreeMachineResponse;

import java.util.ArrayList;

import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class DialogMachines extends DialogFragment{

    ListView freeMachine;
    Button enroll;
    ArrayList<Machines> washingMachines = new ArrayList<>();
    AdapterMachine adapterMachine;

    private Subscription subscription;

    String idMachine;

    SharedPreferences sharedPreferences;

    boolean recap = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_machines, container, false);

        freeMachine = (ListView)view.findViewById(R.id.freeMachine);
        enroll = (Button)view.findViewById(R.id.enroll);


        sharedPreferences = getActivity().getSharedPreferences("savedData", 0);
        String token = sharedPreferences.getString("token", "");
        String date = ((Button)getActivity().findViewById(R.id.dateDialog)).getText().toString();
        String time = ((TextView)getActivity().findViewById(R.id.idTimeChoose)).getText().toString();
        String dormitory = sharedPreferences.getString("dormitory", "");

        ConnectApi connectApi = ConnectApi.getInstanse();

        subscription = connectApi.getMachine("Token " + token, date, time, dormitory)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .subscribe(new Subscriber<Response<FreeMachineResponse>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("MainActivity", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("MainActivity", "onError => " + e.getMessage());
                    }

                    @Override
                    public void onNext(Response<FreeMachineResponse> response) {
                        Log.d("MainActivity", "onNext => " + response);
                        if(response.isSuccessful()){
                            if(!recap){
                                fillModel(response, response.body().getWashingMachines().size());
                                recap = true;
                            }
                            adapterMachine = new AdapterMachine(getActivity(), washingMachines);
                            freeMachine.setAdapter(adapterMachine);
                        }
                    }
                });

        freeMachine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idMachine = ((TextView)view.findViewById(R.id.idMachine)).getText().toString();
                ((TextView)getActivity().findViewById(R.id.numberSelectedMachine)).setText(String.valueOf(idMachine));
                dismiss();
            }
        });

        return view;
    }

    public void fillModel(Response<FreeMachineResponse> freeMachineResponse, int countItems){

        for (int i = 0; i < countItems; i++) {

            washingMachines.add(new Machines(freeMachineResponse.body().getWashingMachines().get(i).getId(),
                    freeMachineResponse.body().getWashingMachines().get(i).getNumber()));

        }

    }



}
