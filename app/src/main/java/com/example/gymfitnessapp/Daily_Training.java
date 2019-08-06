package com.example.gymfitnessapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gymfitnessapp.Database.GymDB;
import com.example.gymfitnessapp.Model.Exercise;
import com.example.gymfitnessapp.Utils.Common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import info.hoang8f.widget.FButton;
import pl.droidsonroids.gif.GifImageView;

public class Daily_Training extends AppCompatActivity {

    FButton btnStart;
    GifImageView detail_gif;
    TextView txtGetReady, txtCountDown, txtTimer, title;
    ProgressBar progressBar;
    LinearLayout linearLayout;

    int ex_id = 0;
    List<Exercise> list = new ArrayList<>();

    GymDB gymDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__training);

        showPosture();

        btnStart = (FButton)findViewById(R.id.btnStartViewExercise);
        detail_gif = (GifImageView)findViewById(R.id.detail_image);
        txtCountDown = (TextView)findViewById(R.id.txtCountDown);
        txtGetReady = (TextView)findViewById(R.id.txtGetReady);
        txtTimer = (TextView)findViewById(R.id.timer);
        linearLayout = (LinearLayout)findViewById(R.id.layout_get_ready);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        title = (TextView)findViewById(R.id.title);

        gymDB = new GymDB(this);

        //set data
        progressBar.setMax(list.size());
        setExerciseInformation(ex_id);
        detail_gif.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnStart.getText().toString().toLowerCase().equals("start")){
                    showGetReady();
                }
                else if (btnStart.getText().toString().toLowerCase().equals("done"))
                {

                    if (gymDB.getSettingMode() == 0)
                        countDownTimerForEasyMode.cancel();
                    else if (gymDB.getSettingMode() == 1)
                        countDownTimerForMediumMode.cancel();
                    else if (gymDB.getSettingMode() == 2)
                        countDownTimerForHardMode.cancel();

                    restTimeCountDownTimer.cancel();

                    if (ex_id < list.size())
                    {
                        showRestTime();
                        ex_id ++;
                        progressBar.setProgress(ex_id);
                        txtTimer.setText("");
                    }
                    else
                        showFinished();
                }

                else
                    if (ex_id < list.size())
                        setExerciseInformation(ex_id);

                    else
                        showFinished();
            }
        });
    }

    private void showGetReady() {

        txtGetReady.setText("GET READY");
        new CountDownTimer(4000,1000) {

            @Override
            public void onTick(long lng) {
                txtCountDown.setText(""+(lng/1000));
                txtCountDown.setTextColor(getResources().getColor(R.color.colorBlack));
                txtCountDown.setVisibility(View.VISIBLE);

                detail_gif.setVisibility(View.INVISIBLE);
                btnStart.setVisibility(View.INVISIBLE);
                txtTimer.setVisibility(View.VISIBLE);

                linearLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                showExercise();
            }
        }
        .start();
    }

    private void showExercise() {

        //list size contain all posture
        if (ex_id < list.size())
        {
            detail_gif.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.INVISIBLE);

            if (gymDB.getSettingMode() == 0)
                countDownTimerForEasyMode.start();
            else if (gymDB.getSettingMode() == 1)
                countDownTimerForMediumMode.start();
            else if (gymDB.getSettingMode() == 2)
                countDownTimerForHardMode.start();

            //set picture and name
            detail_gif.setImageResource(list.get(ex_id).getImage_id());
            title.setText(list.get(ex_id).getName());
            title.setVisibility(View.VISIBLE);
            btnStart.setText("done");
        }
        else
            showFinished();
    }

    private void showRestTime() {
        detail_gif.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        btnStart.setText("skip");
        btnStart.setVisibility(View.VISIBLE);
        title.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);

        restTimeCountDownTimer.start();
        txtGetReady.setText("Rest Time");
    }


    private void showFinished() {

        detail_gif.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        title.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        txtGetReady.setText(R.string.done_today_exercises);
        txtCountDown.setVisibility(View.GONE);

        if (gymDB.getSettingMode() == 0)
            countDownTimerForEasyMode.cancel();
        else if (gymDB.getSettingMode() == 1)
            countDownTimerForMediumMode.cancel();
        else if (gymDB.getSettingMode() == 2)
            countDownTimerForHardMode.cancel();

        restTimeCountDownTimer.cancel();

        //save to database
        gymDB.saveDay("" + Calendar.getInstance().getTimeInMillis());
    }

    //count down
    CountDownTimer countDownTimerForEasyMode = new CountDownTimer(Common.TIME_LIMIT_EASY,1000) {
        @Override
        public void onTick(long lng) {
            txtTimer.setText(""+(lng/1000));
        }

        @Override
        public void onFinish() {
            if (ex_id < list.size() -1)
            {
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("start");
            }
            else {
                showFinished();
            }
        }
    };

    CountDownTimer countDownTimerForMediumMode = new CountDownTimer(Common.TIME_LIMIT_MEDIUM,1000) {
        @Override
        public void onTick(long lng) {
            txtTimer.setText(""+(lng/1000));
        }

        @Override
        public void onFinish() {
            if (ex_id < list.size() -1)
            {
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("start");
            }
            else {
                showFinished();
            }
        }
    };

    CountDownTimer countDownTimerForHardMode = new CountDownTimer(Common.TIME_LIMIT_HARD,1000) {
        @Override
        public void onTick(long lng) {
            txtTimer.setText(""+(lng/1000));
        }

        @Override
        public void onFinish() {
            if (ex_id < list.size() -1)
            {
                ex_id++;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("start");
            }
            else {
                showFinished();
            }
        }
    };


    CountDownTimer restTimeCountDownTimer = new CountDownTimer(10000,1000) {
        @Override
        public void onTick(long lng) {
            txtCountDown.setText(""+(lng/1000));
        }

        @Override
        public void onFinish() {
            if (ex_id < list.size() ) {
                setExerciseInformation(ex_id);
                showExercise();
            }
            else
            {
                showFinished();
            }
        }
    };


    private void setExerciseInformation(int id) {
        title.setText(list.get(id).getName());
        detail_gif.setImageResource(list.get(id).getImage_id());
        btnStart.setText("start");

        detail_gif.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        linearLayout.setVisibility(View.INVISIBLE);
    }

    private void showPosture() {

        list.add(new Exercise(R.drawable.bench_press,"Bench Press"));
        list.add(new Exercise(R.drawable.bent_over_dumbbell_lateral_raise,"Bench Over Dumbbell Lateral Raise"));
        list.add(new Exercise(R.drawable.bent_over_row,"Bent Over Row"));
        list.add(new Exercise(R.drawable.big_arm_circle,"Big Arm Circle"));
        list.add(new Exercise(R.drawable.bulgarian_split_squash,"Bulgarian Split Push"));
        list.add(new Exercise(R.drawable.butt_kicker,"Butt Kicker"));
        list.add(new Exercise(R.drawable.chair_squat,"Chair Squat"));
        list.add(new Exercise(R.drawable.dumbbell_overhead_squad,"Dumbbell Overhead Squad"));
        list.add(new Exercise(R.drawable.farmers_walk,"Farmer's Walk"));
        list.add(new Exercise(R.drawable.forward_lunge_with_twist,"Forward Lunge With Twist"));
        list.add(new Exercise(R.drawable.high_knee_march,"High Knee March"));
        list.add(new Exercise(R.drawable.hip_airplane,"Hip Airplane"));
        list.add(new Exercise(R.drawable.jumping_jacks,"Jumping Jacks"));
        list.add(new Exercise(R.drawable.lunges,"Lunges"));
        list.add(new Exercise(R.drawable.lying_down_leg_raises,"Lying Down Leg Raises"));
        list.add(new Exercise(R.drawable.pile_squat,"Pile Squat"));
        list.add(new Exercise(R.drawable.pile_squat_with_knee_to_elbow,"Pile Squat with Knee to Elbow"));
        list.add(new Exercise(R.drawable.pile_squat_with_upright_row,"Pile Squat with Upright Row"));
        list.add(new Exercise(R.drawable.plank_jump_in,"Plank Jump In"));
        list.add(new Exercise(R.drawable.plank_with_arm_raise,"Plank with Arm Raise"));
        list.add(new Exercise(R.drawable.pull_bent_over_row,"Pull Bent Over Row"));
        list.add(new Exercise(R.drawable.pull_standing_row,"Pull Standing Row"));
        list.add(new Exercise(R.drawable.pull_ups,"Pull Ups"));
        list.add(new Exercise(R.drawable.push_shoulder_press,"Push Shoulder Press"));
        list.add(new Exercise(R.drawable.push_standing_chest_press,"Push Standing Chest Press"));
        list.add(new Exercise(R.drawable.push_ups,"Push Ups"));
        list.add(new Exercise(R.drawable.renegade_row,"Renegade Row"));
        list.add(new Exercise(R.drawable.reverse_lunge_with_front_kick,"Reverse Lunge with Front Kick"));
        list.add(new Exercise(R.drawable.reverse_lunge_with_overhead_press,"Reverse Lunge with Overhead Press"));
        list.add(new Exercise(R.drawable.romanian_deadlift,"Romanian Deadlift"));
        list.add(new Exercise(R.drawable.russian_twist,"Russian Twist"));
        list.add(new Exercise(R.drawable.scissor_hops,"Scissor Hops"));
        list.add(new Exercise(R.drawable.seated_leg_pull_in,"Seated Leg Pull In"));
        list.add(new Exercise(R.drawable.side_lung_with_floor_touch,"Side Lung with Floor Touch"));
        list.add(new Exercise(R.drawable.squat_with_side_leg_lift,"Squat with Side Leg Lift"));
        list.add(new Exercise(R.drawable.standing_rope_face_pull,"Standing Rope Face Pull"));
        list.add(new Exercise(R.drawable.stepup_with_lateral_raise,"Step Up with Lateral Raise"));
        list.add(new Exercise(R.drawable.stepups,"Step Up"));
        list.add(new Exercise(R.drawable.superman,"Superman"));
        list.add(new Exercise(R.drawable.the_good_morning,"Good Morning"));
        list.add(new Exercise(R.drawable.touch_floor_squat_jump,"Touch Floor Squat Jump"));
        list.add(new Exercise(R.drawable.windshield_wipers,"Windshield Wipers"));
    }
}
