package com.fleury.marc.moodtracker.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

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



        //for(int i = 0; i < moodTable.size(); i++) {}

    }


    private void updateLayout(RelativeLayout relativeLayout, int mood) {

        switch(mood){
            case 0:
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams (((metrics.widthPixels * 20) / 100), 0));
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.faded_red));
                break;
            case 1:
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams (((metrics.widthPixels * 40) / 100), 0));
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                break;
            case 2:
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams (((metrics.widthPixels * 60) / 100), 0));
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                break;
            case 3:
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams (((metrics.widthPixels * 80) / 100), 0));
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.light_sage));
                break;
            case 4:
                relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams (metrics.widthPixels, 0));
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                break;
        }

    }


    private void clickComment(ImageView comButton, final String comString) {

        comButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User clicked the comment button
                Toast.makeText(HistoryActivity.this, comString, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
