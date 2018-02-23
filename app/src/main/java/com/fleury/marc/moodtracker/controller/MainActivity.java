package com.fleury.marc.moodtracker.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.model.MoodEnum;
import com.fleury.marc.moodtracker.view.DisappointedFragment;
import com.fleury.marc.moodtracker.view.HappyFragment;
import com.fleury.marc.moodtracker.view.NormalFragment;
import com.fleury.marc.moodtracker.view.SadFragment;
import com.fleury.marc.moodtracker.view.SuperHappyFragment;

import java.util.Calendar;

public class MainActivity extends FragmentActivity {

    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getPreferences(MODE_PRIVATE);

        ViewPager pager = findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(3);

        nextDay(); // NullPointerException : SharedPreferences


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


    private void nextDay() {

        if (mPreferences.getInt("date", 0) == 0) { // If "date" == 0 : it's empty
            mPreferences.edit().putInt("date", Calendar.DAY_OF_YEAR).apply();
        } else {
            mPreferences.edit().putInt("currentDate", Calendar.DAY_OF_YEAR).apply();
        }

        if (mPreferences.getInt("date", 0) < mPreferences.getInt("currentDay", 0)) { // If it's next day :
            // Sending out mPreferences.getInt("mood", 5) to the Hashmap of HistoryActivity on position 0
            // Stocking the comment
            mPreferences.edit().remove("mood").apply(); // Clearing variable "mood"
            mPreferences.edit().remove("comment").apply(); // Clearing variable "comment"
            mPreferences.edit().putInt("date", mPreferences.getInt("currentDate", 0));
        } else {
            // Nothing
        }
    }

}
