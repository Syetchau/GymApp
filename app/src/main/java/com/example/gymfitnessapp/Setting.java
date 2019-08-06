package com.example.gymfitnessapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.gymfitnessapp.Database.GymDB;

import java.util.Calendar;
import java.util.Date;

import info.hoang8f.widget.FButton;

public class Setting extends AppCompatActivity {

    FButton btnSave;
    RadioButton rdyEasy,rdyMedium,rdyHard;
    RadioGroup radioGroup;
    GymDB gymDB;
    ToggleButton switchAlarm;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnSave = (FButton)findViewById(R.id.btnSave);
        radioGroup = (RadioGroup)findViewById(R.id.ready);
        rdyEasy = (RadioButton)findViewById(R.id.rdyEasy);
        rdyMedium = (RadioButton)findViewById(R.id.rdyMedium);
        rdyHard = (RadioButton)findViewById(R.id.rdyHard);
        switchAlarm = (ToggleButton)findViewById(R.id.switchAlarm);
        timePicker = (TimePicker)findViewById(R.id.timePicker);

        gymDB = new GymDB(this);

        int mode = gymDB.getSettingMode();
        setRadioButton(mode);

        //event
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWorkOutMode();
                saveAlarm(switchAlarm.isChecked());
                Toast.makeText(Setting.this, R.string.saved, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void saveAlarm(boolean checked) {

        if (checked) {
            Intent alarmIntent;
            PendingIntent pendingIntent;
            int Hour, Minute;

            alarmIntent = new Intent(Setting.this, AlarmNotificationReceiver.class);

            pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            if (Build.VERSION.SDK_INT >= 23) {

                Hour = timePicker.getHour();
                Minute = timePicker.getMinute();

            } else {

                Minute = timePicker.getCurrentMinute();
                Hour = timePicker.getCurrentHour();
            }

            Date dat = new Date();
            Calendar cal_alarm = Calendar.getInstance();
            Calendar cal_now = Calendar.getInstance();
            cal_now.setTime(dat);
            cal_alarm.setTime(dat);
            cal_alarm.set(Calendar.HOUR_OF_DAY, Hour);
            cal_alarm.set(Calendar.MINUTE, Minute);
            cal_alarm.set(Calendar.SECOND, 10);
            if (cal_alarm.before(cal_now)) {
                cal_alarm.add(Calendar.DATE, 1);
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal_alarm.getTimeInMillis(), pendingIntent);
        }
    }

    private void saveWorkOutMode() {
        int selectedID = radioGroup.getCheckedRadioButtonId();
        if (selectedID == rdyEasy.getId())
            gymDB.saveSettingMode(0);
        else if (selectedID == rdyMedium.getId())
            gymDB.saveSettingMode(1);
        else if (selectedID == rdyHard.getId())
            gymDB.saveSettingMode(2);
    }

    private void setRadioButton(int mode) {
        if (mode == 0)
            radioGroup.check(R.id.rdyEasy);
        else if (mode == 1)
            radioGroup.check(R.id.rdyMedium);
        else if (mode == 2)
            radioGroup.check(R.id.rdyHard);
    }
}
