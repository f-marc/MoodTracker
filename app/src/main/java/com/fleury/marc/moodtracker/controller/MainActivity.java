package com.fleury.marc.moodtracker.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.model.MoodEnum;
import com.fleury.marc.moodtracker.view.MyDialogFragment;

import java.util.Calendar;

    public class MainActivity extends FragmentActivity {

    private ImageView mHistoryButton;
    private ImageView mComButton;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_happy);
        //setContentView(R.layout.activity_main);
        //ViewPager pager = findViewById(R.id.viewPager);

        nextDay();

        mPreferences = getPreferences(MODE_PRIVATE);
        mPreferences.edit().putInt("mood", MoodEnum.HAPPY.getMood()).apply(); //Sad = 0; Disappointed = 1; Normal = 2; Happy = 3; SuperHappy = 4;

        mHistoryButton = findViewById(R.id.fragment_happy_hist);
        mComButton = findViewById(R.id.fragment_happy_com);

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User clicked the history button
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });

        mComButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User clicked the comment button
                showAlertDialog();
            }
        });


    }


    private void nextDay() {

        if(mPreferences.getInt("date", 0) == 0) { // If "date" == 0 : it's empty
            mPreferences.edit().putInt("date", Calendar.DAY_OF_YEAR).apply();
        }
        else {
            mPreferences.edit().putInt("currentDate", Calendar.DAY_OF_YEAR).apply();
        }

        if (mPreferences.getInt("date", 0) < mPreferences.getInt("currentDay", 0)) { // If it's next day :
            // Sending out mPreferences.getInt("mood", 5) to the Hashmap of HistoryActivity on position 0
            // Stocking the comment
            mPreferences.edit().remove("mood").apply(); // Clearing variable "mood"
            mPreferences.edit().remove("comment").apply(); // Clearing variable "comment"
            mPreferences.edit().putInt("date", mPreferences.getInt("currentDate", 0));
        }
        else {
            // Nothing
        }
    }


    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        MyDialogFragment alertDialog = MyDialogFragment.newInstance();
        alertDialog.show(fm, "fragment_alert");
    }


}
