package com.example.machine_time.hackaton.net;

import com.example.machine_time.hackaton.net.model.request.Requests;
import com.example.machine_time.hackaton.net.model.request.ServiceByIdRequest;
import com.example.machine_time.hackaton.net.model.request.ServiceRequest;
import com.example.machine_time.hackaton.net.model.request.UserRequest;
import com.example.machine_time.hackaton.net.model.response.ServiceByIdUser;
import com.example.machine_time.hackaton.net.model.response.ServiceResponse;
import com.example.machine_time.hackaton.net.model.response.UserResponse;
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
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public class ConnectApi {

    private static final String URL = "http://maxim-zubarev.esy.es/porsche/api_pr/";
    private static final String AUTH =  "auth.php";
    private static final String SERVICE = "requests.php";
    private static final String GET_SERVICE = "get_requests.php";

    private Retrofit retrofit;

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

    public Observable<Response<UserResponse>> auth(UserRequest user){
        ConnectApiEndPoint request = retrofit.create(ConnectApiEndPoint.class);
        return request.auth(user);
    }

    public Observable<ServiceResponse> service(ServiceRequest serviceRequest){
        ConnectApiEndPoint request = retrofit.create(ConnectApiEndPoint.class);
        return request.service(serviceRequest);
    }

    public Observable<Response<Requests>> getService(String user_id){
        ConnectApiEndPoint request = retrofit.create(ConnectApiEndPoint.class);
        return request.getService(user_id);
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
        Observable<Response<UserResponse>> auth(@Body UserRequest user);
        @POST(SERVICE)
        Observable<ServiceResponse> service(@Body ServiceRequest serviceRequest);
        @GET(GET_SERVICE)
        Observable<Response<Requests>> getService(@Query("user_id") String user_id);
    }

}
