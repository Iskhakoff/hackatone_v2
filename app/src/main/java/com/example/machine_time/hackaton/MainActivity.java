package com.example.machine_time.hackaton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.machine_time.hackaton.net.ConnectApi;
import com.example.machine_time.hackaton.net.model.request.AuthUserRequest;
import com.example.machine_time.hackaton.net.model.response.AuthUserResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.loginAuthEt)
    EditText loginAuthEt;
    @BindView(R.id.passwordAuthEt)
    EditText passwordAuthEt;
    @BindView(R.id.authBtn)
    Button authBtn;
    @BindView(R.id.registrationBtn)
    Button registrationBtn;

    private Subscription subscription;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        hide action bar
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        authBtn.setOnClickListener(this);
        registrationBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(!loginAuthEt.getText().toString().equalsIgnoreCase("") && !passwordAuthEt.getText().toString().equalsIgnoreCase("")){
            ConnectApi connectApi = ConnectApi.getInstanse();

            AuthUserRequest authUserRequest = new AuthUserRequest();
            authUserRequest.setUsername(loginAuthEt.getText().toString());
            authUserRequest.setPassword(passwordAuthEt.getText().toString());

            switch (v.getId()){
                case R.id.authBtn:
                    subscription = connectApi.auth(authUserRequest)
                            .subscribeOn(Schedulers.io())
                            .observeOn(mainThread())
                            .subscribe(new Subscriber<Response<AuthUserResponse>>() {
                                @Override
                                public void onCompleted() {
                                    Log.d("MainActivity", "onCompleted");
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e("MainActivity", "onError => " + e.getMessage());
                                }

                                @Override
                                public void onNext(Response<AuthUserResponse> response) {
                                    Log.d("MainActivity", "onNext => " + response);
                                    if(response.isSuccessful()){

                                        sharedPreferences = getSharedPreferences("savedData", 0);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("token", response.body().getToken());
                                        editor.putString("id", String.valueOf(response.body().getUser().getId()));
                                        editor.putString("dormitory", String.valueOf(response.body().getUser().getProfile().getDormitory()));
                                        editor.apply();

                                        Intent auth = new Intent(getApplicationContext(), MainPage.class);
                                        startActivity(auth);
                                    }else{
                                        Toast.makeText(MainActivity.this, "Неверный логин или пароль!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    break;



            }
        }else{
            Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
