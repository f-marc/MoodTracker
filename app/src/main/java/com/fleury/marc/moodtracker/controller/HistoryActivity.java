package com.fleury.marc.moodtracker.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.fleury.marc.moodtracker.R;

/**
 * Created by Marc FLEURY on 29/01/2018.
 */

public class HistoryActivity extends AppCompatActivity {

    TextView historyOne, historyTwo, historyThree, historyFour, historyFive, historySix, historySeven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyOne = findViewById(R.id.activity_history_one);
        historyTwo = findViewById(R.id.activity_history_two);
        historyThree = findViewById(R.id.activity_history_three);
        historyFour = findViewById(R.id.activity_history_four);
        historyFive = findViewById(R.id.activity_history_five);
        historySix = findViewById(R.id.activity_history_six);
        historySeven = findViewById(R.id.activity_history_seven);

        //Pour modifier l'affichage en fonction des humeurs :
        //setBackgroundColor(int color)
        //setWidth(int)



    }
}
