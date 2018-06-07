package com.example.machine_time.hackaton;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.machine_time.hackaton.net.ConnectApi;
import com.example.machine_time.hackaton.net.model.request.ServiceRequest;
import com.example.machine_time.hackaton.net.model.response.ServiceResponse;

import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class ServiceFragment extends Fragment {
    Spinner spinner;
    EditText descriptionTroubleEt;
    EditText dateEt;
    Button btnSend;

    private Subscription subscription;
    private int id;
    private SharedPreferences sharedPreferences;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.service_fragment, container, false);

        spinner = (Spinner)v.findViewById(R.id.spinner);
        descriptionTroubleEt = (EditText)v.findViewById(R.id.descriptionTroubleEt);
        dateEt = (EditText)v.findViewById(R.id.dateEt);
        btnSend = (Button)v.findViewById(R.id.btnSend);

        getActivity().setTitle("Заполнить заявку");

//        sharedPreferences = getActivity().getSharedPreferences("savedData", 0);
//        id = Integer.parseInt(sharedPreferences.getString("id", ""));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {

            ConnectApi connectApi = ConnectApi.getInstanse();

            @Override
            public void onClick(View v) {

                ServiceRequest serviceRequest = new ServiceRequest();
                serviceRequest.setType(spinner.getSelectedItem().toString());
                serviceRequest.setInfo(descriptionTroubleEt.getText().toString());
                serviceRequest.setDate(dateEt.getText().toString());
                serviceRequest.setUser_id(id);

                subscription = connectApi.service(serviceRequest)
                        .subscribeOn(Schedulers.io())
                        .observeOn(mainThread())
                        .subscribe(new Subscriber<ServiceResponse>() {
                            @Override
                            public void onCompleted() {
                                Log.d("MainActivity", "onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e("MainActivity", "onError => " + e.getMessage());
                            }

                            @Override
                            public void onNext(ServiceResponse response) {
                                Log.d("MainActivity", "onNext => " + response);

                                try{
                                    if(response.getStatus() == 200){
                                        Toast.makeText(getActivity(), "Заявка отправлена!", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(getActivity(), "Произошла ошибка, попробуйте снова.", Toast.LENGTH_LONG).show();
                                    }
                                }catch (NullPointerException e){
                                    Log.e("MainActivity", "NullPointerException => " + e.getMessage());
                                }
                            }
                        });
            }
        });

        return v;
    }
}
