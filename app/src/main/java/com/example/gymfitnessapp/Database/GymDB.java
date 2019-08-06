package com.example.gymfitnessapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class GymDB extends SQLiteAssetHelper {

    private static final String DB_NAME = "gym.db";
    private static final int DB_VERSION = 1;

    public GymDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public int getSettingMode(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Mode"};
        String sqlTable = "Setting";

        sqLiteQueryBuilder.setTables(sqlTable);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, sqlSelect,
                null, null, null, null, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex("Mode"));
    }

    public void saveSettingMode(int value){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "UPDATE Setting SET Mode = " + value;
        sqLiteDatabase.execSQL(query);
    }

    public List<String> getWorkoutDays(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        String[] sqlSelect = {"Day"};
        String sqlTable = "WorkoutDays";

        sqLiteQueryBuilder.setTables(sqlTable);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, sqlSelect,
                null, null, null, null, null);

        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do {
                result.add(cursor.getString(cursor.getColumnIndex("Day")));
            }while (cursor.moveToNext());
        }
        return result;
    }

    public void saveDay(String value){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = String.format("INSERT INTO WorkoutDays(Day) VALUES('%s');",value);
        sqLiteDatabase.execSQL(query);
    }
}
