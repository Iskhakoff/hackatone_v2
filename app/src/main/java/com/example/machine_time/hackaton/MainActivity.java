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

import com.example.machine_time.hackaton.net.ConnectApi;
import com.example.machine_time.hackaton.net.model.request.UserRequest;
import com.example.machine_time.hackaton.net.model.response.UserResponse;

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
//        hide status bar
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        authBtn.setOnClickListener(this);
        registrationBtn.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("savedData", 0);
        String isAuth = sharedPreferences.getString("ifAuth", "");

        if(isAuth.equalsIgnoreCase(String.valueOf(1))){
            Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);
        }


    }

    @Override
    public void onClick(View v) {
        ConnectApi connectApi = ConnectApi.getInstanse();

        UserRequest user = new UserRequest();

        user.setLogin(loginAuthEt.getText().toString());
        user.setPass(passwordAuthEt.getText().toString());


        switch (v.getId()){
            case R.id.authBtn:
//                subscription = connectApi.auth(user)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(mainThread())
//                        .subscribe(new Subscriber<Response<UserResponse>>() {
//                            @Override
//                            public void onCompleted() {
//                                Log.d("MainActivity", "onCompleted");
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.e("MainActivity", "onError => " + e.getMessage());
//
//                            }
//                            @Override
//                            public void onNext(Response<UserResponse> response) {
//                                Log.d("MainActivity", "onNext => " + response);
//                                try{
//                                    if(response.isSuccessful()){
//                                        sharedPreferences = getSharedPreferences("savedData", 0);
//                                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                                        editor.putString("id", String.valueOf(response.body().getId()));
//                                        editor.putString("name", response.body().getName());
//                                        editor.putString("login", response.body().getLogin());
//                                        editor.putString("build", response.body().getBuild());
//                                        editor.putString("room", response.body().getRoom());
//                                        editor.putString("phone", response.body().getPhone());
//                                        editor.putString("email", response.body().getEmail());
//                                        editor.putString("pass", passwordAuthEt.getText().toString());
//                                        editor.putString("is_employee", response.body().toString());
//                                        editor.putString("isAuth", String.valueOf(1));
//                                        editor.apply();
//                                        Intent authSuccess = new Intent(getApplicationContext(), MainPage.class);
//                                        startActivity(authSuccess);
//                                    }else if(response.code() == 400){
//                                        System.out.println(response.message());
//                                    }else if(response.code() == 404){
//                                        System.out.println(response.message());
//                                    }
//                                }catch (NullPointerException e){
//                                    Log.e("MainActivity", "NullPointerException => " + e.getMessage());
//                                }
//                            }
//                        });
//                break;
                Intent intent = new Intent(this, MainPage.class);
                startActivity(intent);
                break;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        sharedPreferences.edit().clear().apply();
    }
}
