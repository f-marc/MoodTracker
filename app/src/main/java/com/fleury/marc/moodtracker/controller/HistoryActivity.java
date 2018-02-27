package com.fleury.marc.moodtracker.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fleury.marc.moodtracker.R;

import java.util.Calendar;

public class HistoryActivity extends AppCompatActivity {

    private ImageView comOne, comTwo, comThree, comFour, comFive, comSix, comSeven;
    private Calendar mCalendar = Calendar.getInstance();
    private int dayOfYear = mCalendar.get(Calendar.DAY_OF_YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        SharedPreferences mPreferences = getPreferences(MODE_PRIVATE);

        // Test en dur :
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 1) + " mood", 3).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 2) + " mood", 0).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 3) + " mood", 1).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 4) + " mood", 4).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 5) + " mood", 3).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 6) + " mood", 2).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 7) + " mood", 4).apply();

        RelativeLayout relativeOne, relativeTwo, relativeThree, relativeFour, relativeFive, relativeSix, relativeSeven;
        RelativeLayout[] relativeList;
        int[] moodTable = {mPreferences.getInt(String.valueOf(dayOfYear - 1) + " mood", 5),
                           mPreferences.getInt(String.valueOf(dayOfYear - 2) + " mood", 5),
                           mPreferences.getInt(String.valueOf(dayOfYear - 3) + " mood", 5),
                           mPreferences.getInt(String.valueOf(dayOfYear - 4) + " mood", 5),
                           mPreferences.getInt(String.valueOf(dayOfYear - 5) + " mood", 5),
                           mPreferences.getInt(String.valueOf(dayOfYear - 6) + " mood", 5),
                           mPreferences.getInt(String.valueOf(dayOfYear - 7) + " mood", 5)};

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

        relativeList = new RelativeLayout[]{relativeOne, relativeTwo, relativeThree, relativeFour, relativeFive, relativeSix, relativeSeven};

        for(int i = 0; i < 7; i++) {
            updateLayout(relativeList[i], moodTable[i]);
        }

        /*for(int i = 0; i < 7; i++) {
            clickComment();
        }*/

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
