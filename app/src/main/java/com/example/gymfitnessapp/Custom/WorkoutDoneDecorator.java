package com.example.gymfitnessapp.Custom;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.example.gymfitnessapp.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;

public class WorkoutDoneDecorator implements DayViewDecorator {

    HashSet<CalendarDay> calendarDays;
    ColorDrawable colorDrawable;

    public WorkoutDoneDecorator(HashSet<CalendarDay> calendarDays) {
        this.calendarDays = calendarDays;
        colorDrawable = new ColorDrawable(Color.parseColor("#E47373"));
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return calendarDays.contains(day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(colorDrawable);
    }
}
