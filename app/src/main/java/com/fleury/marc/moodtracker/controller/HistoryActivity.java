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

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {

    private Calendar mCalendar = Calendar.getInstance();
    private int mDay = mCalendar.get(Calendar.YEAR) + mCalendar.get(Calendar.DAY_OF_YEAR);

    @BindView(R.id.activity_history_relative_one) RelativeLayout relativeOne;
    @BindView(R.id.activity_history_relative_two) RelativeLayout relativeTwo;
    @BindView(R.id.activity_history_relative_three) RelativeLayout relativeThree;
    @BindView(R.id.activity_history_relative_four) RelativeLayout relativeFour;
    @BindView(R.id.activity_history_relative_five) RelativeLayout relativeFive;
    @BindView(R.id.activity_history_relative_six) RelativeLayout relativeSix;
    @BindView(R.id.activity_history_relative_seven) RelativeLayout relativeSeven;

    @BindView(R.id.activity_history_com_one) ImageView comOne;
    @BindView(R.id.activity_history_com_two) ImageView comTwo;
    @BindView(R.id.activity_history_com_three) ImageView comThree;
    @BindView(R.id.activity_history_com_four) ImageView comFour;
    @BindView(R.id.activity_history_com_five) ImageView comFive;
    @BindView(R.id.activity_history_com_six) ImageView comSix;
    @BindView(R.id.activity_history_com_seven) ImageView comSeven;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        SharedPreferences mPreferences = getPreferences(MODE_PRIVATE);

        RelativeLayout[] relativeList = new RelativeLayout[]{relativeOne, relativeTwo, relativeThree, relativeFour, relativeFive, relativeSix, relativeSeven};
        ImageView[] buttonList = new ImageView[]{comOne, comTwo, comThree, comFour, comFive, comSix, comSeven};

        // List of the seven past moods.
        int[] moodTable = {mPreferences.getInt(String.valueOf(mDay - 1) + " mood", 5),
                mPreferences.getInt(String.valueOf(mDay - 2) + " mood", 5),
                mPreferences.getInt(String.valueOf(mDay - 3) + " mood", 5),
                mPreferences.getInt(String.valueOf(mDay - 4) + " mood", 5),
                mPreferences.getInt(String.valueOf(mDay - 5) + " mood", 5),
                mPreferences.getInt(String.valueOf(mDay - 6) + " mood", 5),
                mPreferences.getInt(String.valueOf(mDay - 7) + " mood", 5)};

        // List of the seven past comments.
        String[] comTable = {mPreferences.getString(String.valueOf(mDay - 1) + " comment", "none"),
                mPreferences.getString(String.valueOf(mDay - 2) + " comment", "none"),
                mPreferences.getString(String.valueOf(mDay - 3) + " comment", "none"),
                mPreferences.getString(String.valueOf(mDay - 4) + " comment", "none"),
                mPreferences.getString(String.valueOf(mDay - 5) + " comment", "none"),
                mPreferences.getString(String.valueOf(mDay - 6) + " comment", "none"),
                mPreferences.getString(String.valueOf(mDay - 7) + " comment", "none")};

        // Updating of all relative layouts and comments buttons.
        for(int i = 0; i < 7; i++) {
            if (moodTable[i] != 5) { // If there is a mood :
                updateLayout(relativeList[i], moodTable[i]);
            }
            if(comTable[i] != "none" && comTable[i] != "") { // If there is a comment :
                updateComButton(buttonList[i], comTable[i]);
            }
        }
    }


    // Method for updating layouts :
    private void updateLayout(RelativeLayout relativeLayout, int mood) {

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) relativeLayout.getLayoutParams();

        switch(mood){
            case 0: // If mood = "Sad" :
                params.width = ((metrics.widthPixels * 20) / 100); // The layout is displayed on 1/5 of the screen width ...
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.faded_red)); // ... And its background is set to red.
                break;
            case 1: // If mood = "Disappointed" :
                params.width = ((metrics.widthPixels * 40) / 100); // The layout is displayed on 2/5 of the screen width ...
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.warm_grey)); // ... And its background is set to grey.
                break;
            case 2: // If mood = "Normal" :
                params.width = ((metrics.widthPixels * 60) / 100); // The layout is displayed on 3/5 of the screen width ...
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65)); // ... And its background is set to blue.
                break;
            case 3: // If mood = "Happy" :
                params.width = ((metrics.widthPixels * 80) / 100); // The layout is displayed on 4/5 of the screen width ...
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.light_sage)); // ... And its background is set to green.
                break;
            case 4: // If mood = "SuperHappy" :
                params.width = metrics.widthPixels; // The layout is displayed on all of the screen width ...
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow)); // ... And its background is set to yellow.
                break;
        }
        relativeLayout.setLayoutParams(params);
        relativeLayout.setVisibility(View.VISIBLE);
    }


    // Method for updating comment button's ImageViews.
    private void updateComButton(ImageView comButton, final String comment) {
        comButton.setVisibility(View.VISIBLE); // The associated icon is displayed.

        comButton.setOnClickListener(new View.OnClickListener() { // If the user clicked the icon, then the comment is displayed by a toast message.
            @Override
            public void onClick(View v) {
                // User clicked the comment button.
                Toast.makeText(HistoryActivity.this, comment, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
