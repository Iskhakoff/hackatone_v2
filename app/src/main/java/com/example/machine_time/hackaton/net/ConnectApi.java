package com.example.machine_time.hackaton.net;

import android.content.SharedPreferences;

import com.example.machine_time.hackaton.net.model.Machines;
import com.example.machine_time.hackaton.net.model.request.AuthUserRequest;
import com.example.machine_time.hackaton.net.model.request.WashingScheduleRequest;
import com.example.machine_time.hackaton.net.model.response.AuthUserResponse;
import com.example.machine_time.hackaton.net.model.response.FreeMachineResponse;
import com.example.machine_time.hackaton.net.model.response.TimeLine;
import com.example.machine_time.hackaton.net.model.response.WashingScheduleResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class ConnectApi {

    private static final String URL  = "https://d-services.herokuapp.com/api/";
    private static final String AUTH = "token-auth/";
    private static final String GET_MACHINE = "free-washing-machines/";
    private static final String GET_TIME = "free-washing-time/";
    private static final String ENROLL_WASH = "washing-schedule/";

    private Retrofit retrofit;

    private SharedPreferences sharedPreferences;


    private ConnectApi(){
        init();
    }

    private void init() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder().setLenient().create();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static ConnectApi getInstanse(){
        return ConnectApi.ConnectApiHolder.getInstanse();
    }

    public Observable<Response<AuthUserResponse>> auth(AuthUserRequest user){
        ConnectApiEndPoint request = retrofit.create(ConnectApiEndPoint.class);
        return request.auth(user);
    }

    public Observable<Response<FreeMachineResponse>> getMachine(String token, String date, String time, String dormitory){
        ConnectApiEndPoint request = retrofit.create(ConnectApiEndPoint.class);
        return request.getMachine(token, date, time, dormitory);
    }

    public Observable<Response<TimeLine>> getTime(String token, String date, String dormitory){
        ConnectApiEndPoint request = retrofit.create(ConnectApiEndPoint.class);
        return request.getTime(token, date, dormitory);
    }

    public Observable<Response<WashingScheduleResponse>> enroll(String token, WashingScheduleRequest washingScheduleRequest){
        ConnectApiEndPoint request = retrofit.create(ConnectApiEndPoint.class);
        return request.enroll(token, washingScheduleRequest);
    }




    private static class ConnectApiHolder{
        private static ConnectApi instanse;

        static ConnectApi getInstanse(){
            if(instanse == null){
                instanse = new ConnectApi();
            }else{
                instanse.init();
            }
            return instanse;
        }
    }

    public interface ConnectApiEndPoint{
        @POST(AUTH)
        Observable<Response<AuthUserResponse>> auth(@Body AuthUserRequest user);
        @GET(GET_MACHINE)
        Observable<Response<FreeMachineResponse>> getMachine(@Header("Authorization") String token, @Query("date") String date, @Query("time") String time,
                                                             @Query("dormitory") String dormitory);
        @GET(GET_TIME)
        Observable<Response<TimeLine>> getTime(@Header("Authorization") String token, @Query("date") String date, @Query("dormitory") String dormitory);
        @POST(ENROLL_WASH)
        Observable<Response<WashingScheduleResponse>> enroll(@Header("Authorization") String token, @Body WashingScheduleRequest washingScheduleRequest);
    }

}
