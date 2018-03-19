package com.fleury.marc.moodtracker.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.model.MoodEnum;
import com.fleury.marc.moodtracker.view.DisappointedFragment;
import com.fleury.marc.moodtracker.view.HappyFragment;
import com.fleury.marc.moodtracker.view.NormalFragment;
import com.fleury.marc.moodtracker.view.SadFragment;
import com.fleury.marc.moodtracker.view.SuperHappyFragment;

import java.util.Calendar;

public class MainActivity extends FragmentActivity {

    SharedPreferences mPreferences;
    private Calendar mCalendar = Calendar.getInstance();
    private int mDay = mCalendar.get(Calendar.YEAR) + mCalendar.get(Calendar.DAY_OF_YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getPreferences(MODE_PRIVATE);

        ViewPager pager = findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(3); // We set the default display to "happy".

        saveMood();
        playSound();

        nextDay();
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {
                case 0:
                    return SadFragment.newInstance();
                case 1:
                    return DisappointedFragment.newInstance();
                case 2:
                    return NormalFragment.newInstance();
                case 3:
                    return HappyFragment.newInstance();
                case 4:
                    return SuperHappyFragment.newInstance();
                default:
                    return HappyFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }


    // Method for detecting day change :
    private void nextDay() {

        if (mPreferences.getInt("date", 0) == 0) { // If "date" == 0 : first launch
            mPreferences.edit().putInt("date", mDay).apply();
        } else {
            mPreferences.edit().putInt("currentDate", mDay).apply();
        }

        if (mPreferences.getInt("date", 0) < mPreferences.getInt("currentDay", 0)) { // If it's next day :
            for(int i = 1; i < 359; i++) {
                mPreferences.edit().remove(String.valueOf(mDay - (8 + i)) + " mood").apply(); // We delete all useless mood's preferences ...
                mPreferences.edit().remove(String.valueOf(mDay - (8 + i)) + " comment").apply(); // ... And same for comment's ones.
            }
            mPreferences.edit().putInt("date", mPreferences.getInt("currentDate", 0)).apply();
            mPreferences.edit().remove("currentDate").apply();
        }
    }


    // Method for saving mood.
    public void saveMood() {
        mPreferences.edit().putInt(String.valueOf(mDay) + " mood", MoodEnum.HAPPY.getMood()).apply();
        Log.i("MoodTest", String.valueOf(mPreferences.getInt(String.valueOf(mDay) + " mood", 5)));
    }


    // Method for playing a sound.
    public void playSound() {
        MediaPlayer mSound = MediaPlayer.create(this, R.raw.g3);
        mSound.start();
    }
}
