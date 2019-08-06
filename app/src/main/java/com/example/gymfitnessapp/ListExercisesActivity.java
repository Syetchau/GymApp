package com.example.gymfitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gymfitnessapp.Adapter.RecyclerViewAdapter;
import com.example.gymfitnessapp.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListExercisesActivity extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);

        showPosture();

        recyclerView = (RecyclerView)findViewById(R.id.list_exercise);
        recyclerViewAdapter = new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void showPosture() {

        exerciseList.add(new Exercise(R.drawable.bench_press,"Bench Press"));
        exerciseList.add(new Exercise(R.drawable.bent_over_dumbbell_lateral_raise,"Bench Over Dumbbell Lateral Raise"));
        exerciseList.add(new Exercise(R.drawable.bent_over_row,"Bent Over Row"));
        exerciseList.add(new Exercise(R.drawable.big_arm_circle,"Big Arm Circle"));
        exerciseList.add(new Exercise(R.drawable.bulgarian_split_squash,"Bulgarian Split Push"));
        exerciseList.add(new Exercise(R.drawable.butt_kicker,"Butt Kicker"));
        exerciseList.add(new Exercise(R.drawable.chair_squat,"Chair Squat"));
        exerciseList.add(new Exercise(R.drawable.dumbbell_overhead_squad,"Dumbbell Overhead Squad"));
        exerciseList.add(new Exercise(R.drawable.farmers_walk,"Farmer's Walk"));
        exerciseList.add(new Exercise(R.drawable.forward_lunge_with_twist,"Forward Lunge With Twist"));
        exerciseList.add(new Exercise(R.drawable.high_knee_march,"High Knee March"));
        exerciseList.add(new Exercise(R.drawable.hip_airplane,"Hip Airplane"));
        exerciseList.add(new Exercise(R.drawable.jumping_jacks,"Jumping Jacks"));
        exerciseList.add(new Exercise(R.drawable.lunges,"Lunges"));
        exerciseList.add(new Exercise(R.drawable.lying_down_leg_raises,"Lying Down Leg Raises"));
        exerciseList.add(new Exercise(R.drawable.pile_squat,"Pile Squat"));
        exerciseList.add(new Exercise(R.drawable.pile_squat_with_knee_to_elbow,"Pile Squat with Knee to Elbow"));
        exerciseList.add(new Exercise(R.drawable.pile_squat_with_upright_row,"Pile Squat with Upright Row"));
        exerciseList.add(new Exercise(R.drawable.plank_jump_in,"Plank Jump In"));
        exerciseList.add(new Exercise(R.drawable.plank_with_arm_raise,"Plank with Arm Raise"));
        exerciseList.add(new Exercise(R.drawable.pull_bent_over_row,"Pull Bent Over Row"));
        exerciseList.add(new Exercise(R.drawable.pull_standing_row,"Pull Standing Row"));
        exerciseList.add(new Exercise(R.drawable.pull_ups,"Pull Ups"));
        exerciseList.add(new Exercise(R.drawable.push_shoulder_press,"Push Shoulder Press"));
        exerciseList.add(new Exercise(R.drawable.push_standing_chest_press,"Push Standing Chest Press"));
        exerciseList.add(new Exercise(R.drawable.push_ups,"Push Ups"));
        exerciseList.add(new Exercise(R.drawable.renegade_row,"Renegade Row"));
        exerciseList.add(new Exercise(R.drawable.reverse_lunge_with_front_kick,"Reverse Lunge with Front Kick"));
        exerciseList.add(new Exercise(R.drawable.reverse_lunge_with_overhead_press,"Reverse Lunge with Overhead Press"));
        exerciseList.add(new Exercise(R.drawable.romanian_deadlift,"Romanian Deadlift"));
        exerciseList.add(new Exercise(R.drawable.russian_twist,"Russian Twist"));
        exerciseList.add(new Exercise(R.drawable.scissor_hops,"Scissor Hops"));
        exerciseList.add(new Exercise(R.drawable.seated_leg_pull_in,"Seated Leg Pull In"));
        exerciseList.add(new Exercise(R.drawable.side_lung_with_floor_touch,"Side Lung with Floor Touch"));
        exerciseList.add(new Exercise(R.drawable.squat_with_side_leg_lift,"Squat with Side Leg Lift"));
        exerciseList.add(new Exercise(R.drawable.standing_rope_face_pull,"Standing Rope Face Pull"));
        exerciseList.add(new Exercise(R.drawable.stepup_with_lateral_raise,"Step Up with Lateral Raise"));
        exerciseList.add(new Exercise(R.drawable.stepups,"Step Up"));
        exerciseList.add(new Exercise(R.drawable.superman,"Superman"));
        exerciseList.add(new Exercise(R.drawable.the_good_morning,"Good Morning"));
        exerciseList.add(new Exercise(R.drawable.touch_floor_squat_jump,"Touch Floor Squat Jump"));
        exerciseList.add(new Exercise(R.drawable.windshield_wipers,"Windshield Wipers"));

    }
}
