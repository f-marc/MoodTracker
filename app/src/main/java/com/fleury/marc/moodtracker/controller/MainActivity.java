package com.fleury.marc.moodtracker.controller;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.model.Date;
import com.fleury.marc.moodtracker.model.Mood;
import com.fleury.marc.moodtracker.model.MoodEnum;
import com.fleury.marc.moodtracker.view.MyDialogFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ImageView mHistoryButton;
    private ImageView mComButton;
    private Mood mMood = new Mood();
    private Date mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy);

        nextDay();

        mMood.setMood(MoodEnum.Sad.getMood()); //Sad = 0; Disappointed = 1; Normal = 2; Happy = 3; SuperHappy = 4;

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

        mDate = new Date();

        if(mDate.getDate() == 0) {
            mDate.setDate(Calendar.DAY_OF_YEAR);
        }
        else {
            mDate.setCurrentDate(Calendar.DAY_OF_YEAR);
        }

        if (mDate.getDate() < mDate.getCurrentDate()) { // Si on change de jour :
            // On voie le mMood.getMood dans le Hashmap de l'HistoryActivity en position 0
            // On stock le potentiel commentaire

            // On vide les variables "mood" et "comment"
            mDate.setDate(mDate.getCurrentDate());
        }
        else {
            // On ne fait rien.
        }
    }


    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        MyDialogFragment alertDialog = MyDialogFragment.newInstance();
        alertDialog.show(fm, "fragment_alert");
    }


}
