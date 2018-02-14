package com.fleury.marc.moodtracker.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.model.MoodEnum;
import com.fleury.marc.moodtracker.model.MyDate;
import com.fleury.marc.moodtracker.view.MyDialogFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ImageView mHistoryButton;
    private ImageView mComButton;
    private MyDate mDate;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy);

        nextDay();

        mPreferences = getPreferences(MODE_PRIVATE);
        mPreferences.edit().putInt("mood", MoodEnum.Happy.getMood()).apply(); //Sad = 0; Disappointed = 1; Normal = 2; Happy = 3; SuperHappy = 4;

        mHistoryButton = findViewById(R.id.activity_happy_hist);
        mComButton = findViewById(R.id.activity_happy_com);

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

        mDate = new MyDate();

        if(mDate.getDate() == 0) {
            mDate.setDate(Calendar.DAY_OF_YEAR);
        }
        else {
            mDate.setCurrentDate(Calendar.DAY_OF_YEAR);
        }

        if (mDate.getDate() < mDate.getCurrentDate()) { // If it's next day :
            // Sending out mPreferences.getInt("mood", 5) to the Hashmap of HistoryActivity on position 0
            // Stocking the comment
            mPreferences.edit().remove("mood").apply(); // Clearing variable "mood"
            mPreferences.edit().remove("comment").apply(); // Clearing variable "comment"
            mDate.setDate(mDate.getCurrentDate());
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
