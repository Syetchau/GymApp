package com.example.gymfitnessapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymfitnessapp.Database.GymDB;
import com.example.gymfitnessapp.Utils.Common;

import info.hoang8f.widget.FButton;
import pl.droidsonroids.gif.GifImageView;

public class ViewPosture extends AppCompatActivity {

    int image_id;
    String name;

    TextView timer,title;
    GifImageView detail_image;
    FButton btnStart;

    boolean isRunning = false;

    GymDB gymDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posture);

        gymDB = new GymDB(this);

        timer = (TextView)findViewById(R.id.timer);
        title = (TextView)findViewById(R.id.title);
        detail_image = (GifImageView) findViewById(R.id.detail_image);
        btnStart = (FButton)findViewById(R.id.btnStart);

        timer.setText("");

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isRunning) {

                    int timeLimit = 0;
                    if (gymDB.getSettingMode() == 0)
                        timeLimit = Common.TIME_LIMIT_EASY;
                    else if(gymDB.getSettingMode() == 1)
                        timeLimit = Common.TIME_LIMIT_MEDIUM;
                    else if(gymDB.getSettingMode() == 2)
                        timeLimit = Common.TIME_LIMIT_HARD;

                    new CountDownTimer(timeLimit,1000){

                        @Override
                        public void onTick(long sec) {
                            timer.setText("" +sec/1000);
                            btnStart.setVisibility(View.INVISIBLE);
                            btnStart.setText(R.string.done);
                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(ViewPosture.this, R.string.done, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                }
                else
                {
                    Toast.makeText(ViewPosture.this, R.string.done, Toast.LENGTH_SHORT).show();
                    finish();
                }

                isRunning = !isRunning;
            }
        });

        if(getIntent() != null){
            image_id = getIntent().getIntExtra("image_id", -1);
            name = getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);
        }
    }
}
