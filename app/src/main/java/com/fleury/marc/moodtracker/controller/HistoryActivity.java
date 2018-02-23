package com.fleury.marc.moodtracker.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    RelativeLayout relativeList[] = {relativeOne, relativeTwo, relativeThree, relativeFour, relativeFive, relativeSix, relativeSeven};
    //Map<Integer,  String> comTable = new HashMap<>(); // <Day's key, Comment>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        moodTable.put(0, 3);
        moodTable.put(1, 4);
        moodTable.put(2, 2);
        moodTable.put(3, 1);
        moodTable.put(4, 0);
        moodTable.put(5, 3);
        moodTable.put(6, 2);

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

        for(int i = 0; i < moodTable.size(); i++) { // BUG ICI
            updateLayout(relativeList[i], moodTable.get(i));
        }

    }


    private void updateLayout(RelativeLayout relativeLayout, int mood) {

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();

        switch(mood){
            case 0:
                params.width = ((metrics.widthPixels * 20) / 100);
                relativeLayout.setLayoutParams(params);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.faded_red));
                relativeLayout.setVisibility(View.VISIBLE);
                break;
            case 1:
                params.width = ((metrics.widthPixels * 40) / 100);
                relativeLayout.setLayoutParams(params);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.warm_grey));
                relativeLayout.setVisibility(View.VISIBLE);
                break;
            case 2:
                params.width = ((metrics.widthPixels * 60) / 100);
                relativeLayout.setLayoutParams(params);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65));
                relativeLayout.setVisibility(View.VISIBLE);
                break;
            case 3:
                params.width = ((metrics.widthPixels * 80) / 100);
                relativeLayout.setLayoutParams(params);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.light_sage));
                relativeLayout.setVisibility(View.VISIBLE);
                break;
            case 4:
                params.width = metrics.widthPixels;
                relativeLayout.setLayoutParams(params);
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow));
                relativeLayout.setVisibility(View.VISIBLE);
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
