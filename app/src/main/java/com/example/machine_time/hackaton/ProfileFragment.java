package com.example.machine_time.hackaton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    TextView fullname, username, email, phone, dormitory, room;
    Button exit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.profile_fragment, container, false);

        sharedPreferences = getActivity().getSharedPreferences("savedData", 0);

        fullname = (TextView)v.findViewById(R.id.fullname);
        username = (TextView)v.findViewById(R.id.username);
        email = (TextView)v.findViewById(R.id.email);
        phone = (TextView)v.findViewById(R.id.phone);
        dormitory = (TextView)v.findViewById(R.id.dormitory);
        room = (TextView)v.findViewById(R.id.room);
        exit = (Button)v.findViewById(R.id.exit);

//        fullname.setText(sharedPreferences.getString("fullname", ""));
        username.setText(sharedPreferences.getString("username", ""));
        email.setText(sharedPreferences.getString("email", ""));
        phone.setText(sharedPreferences.getString("phone", ""));
        dormitory.setText(dormitory.getText().toString() + " " + sharedPreferences.getString("dormitory", ""));
        room.setText(room.getText().toString() + " " + sharedPreferences.getString("room", ""));


        exit.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit:
                sharedPreferences.edit().clear().apply();
                Intent exit = new Intent(getActivity(), MainActivity.class);
                startActivity(exit);
        }
    }
}

