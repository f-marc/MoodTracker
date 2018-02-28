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

    private Calendar mCalendar = Calendar.getInstance();
    private int dayOfYear = mCalendar.get(Calendar.DAY_OF_YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        SharedPreferences mPreferences = getPreferences(MODE_PRIVATE);

        // ****************************** TEST EN DUR : ******************************
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 1) + " mood", 3).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 2) + " mood", 0).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 3) + " mood", 1).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 4) + " mood", 4).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 5) + " mood", 3).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 6) + " mood", 2).apply();
        mPreferences.edit().putInt(String.valueOf(dayOfYear - 7) + " mood", 4).apply();

        // ****************************** TEST EN DUR : ******************************
        mPreferences.edit().putString(String.valueOf(dayOfYear - 1) + " comment", "Test hier").apply();
        mPreferences.edit().putString(String.valueOf(dayOfYear - 3) + " comment", "Test il y a 3 jours").apply();
        mPreferences.edit().putString(String.valueOf(dayOfYear - 6) + " comment", "Test il y a 6 jours").apply();


        RelativeLayout relativeOne, relativeTwo, relativeThree, relativeFour, relativeFive, relativeSix, relativeSeven;
        RelativeLayout[] relativeList;
        ImageView comOne, comTwo, comThree, comFour, comFive, comSix, comSeven;
        ImageView[] buttonList;

        int[] moodTable = {mPreferences.getInt(String.valueOf(dayOfYear - 1) + " mood", 5),
                            mPreferences.getInt(String.valueOf(dayOfYear - 2) + " mood", 5),
                            mPreferences.getInt(String.valueOf(dayOfYear - 3) + " mood", 5),
                            mPreferences.getInt(String.valueOf(dayOfYear - 4) + " mood", 5),
                            mPreferences.getInt(String.valueOf(dayOfYear - 5) + " mood", 5),
                            mPreferences.getInt(String.valueOf(dayOfYear - 6) + " mood", 5),
                            mPreferences.getInt(String.valueOf(dayOfYear - 7) + " mood", 5)};

        String[] comTable = {mPreferences.getString(String.valueOf(dayOfYear - 1) + " comment", "none"),
                            mPreferences.getString(String.valueOf(dayOfYear - 2) + " comment", "none"),
                            mPreferences.getString(String.valueOf(dayOfYear - 3) + " comment", "none"),
                            mPreferences.getString(String.valueOf(dayOfYear - 4) + " comment", "none"),
                            mPreferences.getString(String.valueOf(dayOfYear - 5) + " comment", "none"),
                            mPreferences.getString(String.valueOf(dayOfYear - 6) + " comment", "none"),
                            mPreferences.getString(String.valueOf(dayOfYear - 7) + " comment", "none")};


        relativeOne = findViewById(R.id.activity_history_relative_one);
        relativeTwo = findViewById(R.id.activity_history_relative_two);
        relativeThree = findViewById(R.id.activity_history_relative_three);
        relativeFour = findViewById(R.id.activity_history_relative_four);
        relativeFive = findViewById(R.id.activity_history_relative_five);
        relativeSix = findViewById(R.id.activity_history_relative_six);
        relativeSeven = findViewById(R.id.activity_history_relative_seven);

        relativeList = new RelativeLayout[]{relativeOne, relativeTwo, relativeThree, relativeFour, relativeFive, relativeSix, relativeSeven};

        comOne = findViewById(R.id.activity_history_com_one);
        comTwo = findViewById(R.id.activity_history_com_two);
        comThree = findViewById(R.id.activity_history_com_three);
        comFour = findViewById(R.id.activity_history_com_four);
        comFive = findViewById(R.id.activity_history_com_five);
        comSix = findViewById(R.id.activity_history_com_six);
        comSeven = findViewById(R.id.activity_history_com_seven);

        buttonList = new ImageView[]{comOne, comTwo, comThree, comFour, comFive, comSix, comSeven};

        for(int i = 0; i < 7; i++) {
            updateLayout(relativeList[i], moodTable[i]);
        }

        for(int i = 0; i < 7; i++) {
            updateComButton(buttonList[i], comTable[i]);
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

    private void updateComButton(ImageView comButton, final String comment) {
        if(comment != "none") {
            comButton.setVisibility(View.VISIBLE);

            comButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // User clicked the comment button
                    Toast.makeText(HistoryActivity.this, comment, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
