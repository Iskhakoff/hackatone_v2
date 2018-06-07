package com.example.machine_time.hackaton;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.machine_time.hackaton.net.ConnectApi;
import com.example.machine_time.hackaton.net.model.request.Requests;
import com.example.machine_time.hackaton.net.model.request.ServiceByIdRequest;
import com.example.machine_time.hackaton.net.model.request.UserRequest;
import com.example.machine_time.hackaton.net.model.response.ServiceByIdUser;
import com.example.machine_time.hackaton.net.model.response.UserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class ProfileFragment extends Fragment {

    private TextView name, login, campus, room, phone, email;
    private Button exitBtn;
    private ListView servicesUser;
    private Subscription subscription;
    private SharedPreferences sharedPreferences;
    ArrayList<Requests> serviceByIdUsers = new ArrayList<>();
    AdapterList adapterList;

//    ServiceByIdUser obj;
//    Gson gson = new GsonBuilder().setLenient().create();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.profile_fragment, container, false);

        getActivity().setTitle("Профиль");

//        name = (TextView)v.findViewById(R.id.name);
//        login = (TextView)v.findViewById(R.id.login);
//        campus = (TextView)v.findViewById(R.id.campus);
//        room = (TextView)v.findViewById(R.id.room);
//        phone = (TextView)v.findViewById(R.id.phone);
//        email = (TextView)v.findViewById(R.id.email);
        servicesUser = (ListView)v.findViewById(R.id.servicesUser);

        // Put profile info
//        sharedPreferences = getActivity().getSharedPreferences("savedData", 0);
//        String id = sharedPreferences.getString("id", "");
//        String loginUser = sharedPreferences.getString("login", "");
//        String nameUser = sharedPreferences.getString("name", "");
//        String buildUser = sharedPreferences.getString("build", "");
//        String roomUser = sharedPreferences.getString("room", "");
//        String phoneUser = sharedPreferences.getString("phone", "");
//        String emailUser = sharedPreferences.getString("email", "");
//
//        name.setText(nameUser);
//        login.setText(loginUser);
//        campus.setText(buildUser);
//        room.setText(roomUser);
//        phone.setText(phoneUser);
//        email.setText(emailUser);



        ConnectApi connectApi = ConnectApi.getInstanse();
        UserRequest userRequest = new UserRequest();


//        subscription = connectApi.getService(String.valueOf(3))
//                .subscribeOn(Schedulers.io())
//                .observeOn(mainThread())
//                .subscribe(new Subscriber<Response<Requests>>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d("MainActivity", "onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("MainActivity", "onError => " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Response<Requests> response) {
//                        Log.d("MainActivity", "onNext => " + response);
//                        try{
//                            if(response.isSuccessful()){
//
//                            }
//                        }catch (NullPointerException e){
//                            Log.e("MainActivity", "NullPointerException => " + e.getMessage());
//                        }
//
//
//                    }
//                });


//        String json = "{\"requests\": [{\"id\": \"7\", \"created_at\": \"0000-00-00 00:00:00\", \"type\": \"Плотник\", \"info\": \"Чайник\", \"date\": \"06.06.06\", \"status\": \"processing\", \"user_id\": \"3\"},{\"id\": \"11\", \"created_at\": \"0000-00-00 00:00:00\", \"type\": \"Электрик\", \"info\": \"Чайник\", \"date\": \"06.06.1992\", \"status\": \"processing\", \"user_id\": \"3\"},{\"id\": \"12\", \"created_at\": \"2018-05-22 00:00:00\", \"type\": \"Плотник\", \"info\": \"Чайник\", \"date\": \"06.06.06\", \"status\": \"processing\", \"user_id\": \"3\"}]}";
//
//        obj = gson.fromJson(json, ServiceByIdUser.class);
//        fillData();
//        adapterList = new AdapterList(getActivity(), serviceByIdUsers);
//        servicesUser.setAdapter(adapterList);

        return v;


    }

//    void fillData(int countItems, Response<Requests> response){
//        for (int i = 0; i < countItems; i++) {
//            serviceByIdUsers.add(new Requests(response.body().getId(),
//                    response.body().getCreated_ad(),
//                    response.body().getEmployee_id(),
//                    response.body().getProblem_description(),
//                    response.body().getDesired_time(),
//                    response.body().getStatus(),
//                    response.body().getUser_id()));
//        }
//    }

//    void fillData(){
//        for (int i = 0; i < obj.getRequests().size(); i++) {
//            serviceByIdUsers.add(new ServiceByIdUser(obj.getRequests()));
//        }
//    }
}

//int countItems, Response<ServiceByIdUser> response
