package com.fleury.marc.moodtracker.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.model.Mood;

import java.util.Map;
import java.util.HashMap;

public class HistoryActivity extends AppCompatActivity {

    RelativeLayout relativeOne, relativeTwo, relativeThree, relativeFour, relativeFive, relativeSix, relativeSeven;
    ImageView comOne, comTwo, comThree, comFour, comFive, comSix, comSeven;
    Mood mMood = new Mood();
    Map<Integer, Integer> moodTable = new HashMap<>(); // <Day's key, Mood's number>
    Map<Integer,  String> comTable = new HashMap<>(); // <Day's key, Comment>

    DisplayMetrics metrics = getResources().getDisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        relativeOne = findViewById(R.id.activity_history_relative_one);
        relativeTwo = findViewById(R.id.activity_history_relative_two);
        relativeThree = findViewById(R.id.activity_history_relative_three);
        relativeFour = findViewById(R.id.activity_history_relative_four);
        relativeFive = findViewById(R.id.activity_history_relative_five);
        relativeSix = findViewById(R.id.activity_history_relative_six);
        relativeSeven = findViewById(R.id.activity_history_relative_seven);

        comOne = findViewById(R.id.activity_history_com_one);
        comTwo = findViewById(R.id.activity_history_com_two);
        comThree = findViewById(R.id.activity_history_com_three);
        comFour = findViewById(R.id.activity_history_com_four);
        comFive = findViewById(R.id.activity_history_com_five);
        comSix = findViewById(R.id.activity_history_com_six);
        comSeven = findViewById(R.id.activity_history_com_seven);

        for(int i = 0; i < moodTable.size(); i++) {
            switch(moodTable.get(i)){
                case 0:
                    relativeOne.setLayoutParams(new RelativeLayout.LayoutParams (((metrics.widthPixels * 20) / 100), 0));
                    // setBackgroundColor(int color)
                    // setWidth((metrics.widthPixels * 20) / 100)
                    break;
                case 1:
                    relativeOne.setLayoutParams(new RelativeLayout.LayoutParams (((metrics.widthPixels * 40) / 100), 0));
                    // setBackgroundColor(int color)
                    // setWidth((metrics.widthPixels * 40) / 100)
                    break;
                case 2:
                    relativeOne.setLayoutParams(new RelativeLayout.LayoutParams (((metrics.widthPixels * 60) / 100), 0));
                    // setBackgroundColor(int color)
                    // setWidth((metrics.widthPixels * 60) / 100)
                    break;
                case 3:
                    relativeOne.setLayoutParams(new RelativeLayout.LayoutParams (((metrics.widthPixels * 80) / 100), 0));
                    // setBackgroundColor(int color)
                    // setWidth((metrics.widthPixels * 80) / 100)
                    break;
                case 4:
                    relativeOne.setLayoutParams(new RelativeLayout.LayoutParams (metrics.widthPixels, 0));
                    // setBackgroundColor(int color)
                    // setWidth(metrics.widthPixels)
                    break;
            }

        }

    }

}
