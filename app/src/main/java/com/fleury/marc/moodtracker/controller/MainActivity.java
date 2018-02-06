package com.fleury.marc.moodtracker.controller;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.model.Mood;
import com.fleury.marc.moodtracker.view.MyDialogFragment;

public class MainActivity extends AppCompatActivity {

    private ImageView mHistoryButton;
    private ImageView mComButton;
    private Mood mMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy);

        mMood = new Mood();
        mMood.setMood(3); //Sad = 0; Disappointed = 1; Normal = 2; Happy = 3; SuperHappy = 4;

        mHistoryButton = findViewById(R.id.activity_happy_hist);
        mComButton = findViewById(R.id.activity_happy_com);

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User clicked the history button
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
                finish();
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


    private void showAlertDialog() {
        FragmentManager fm = getSupportFragmentManager();
        MyDialogFragment alertDialog = MyDialogFragment.newInstance();
        alertDialog.show(fm, "fragment_alert");
    }


}
