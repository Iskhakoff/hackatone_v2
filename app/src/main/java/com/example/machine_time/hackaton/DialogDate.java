package com.example.machine_time.hackaton;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class DialogDate extends DialogFragment implements View.OnClickListener {

    Button chooseDate;
    DatePicker datePicker;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_date, container, false);

        datePicker = (DatePicker)v.findViewById(R.id.datePicker);
        chooseDate = (Button)v.findViewById(R.id.chooseDate);
        chooseDate.setOnClickListener(this);

        Calendar today = Calendar.getInstance();

        datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ((Button)getActivity().findViewById(R.id.dateDialog)).setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

            }
        });

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chooseDate:
                dismiss();
                break;
        }
    }
}
