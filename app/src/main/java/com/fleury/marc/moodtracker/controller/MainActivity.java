package com.fleury.marc.moodtracker.controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.view.DisappointedFragment;
import com.fleury.marc.moodtracker.view.HappyFragment;
import com.fleury.marc.moodtracker.view.NormalFragment;
import com.fleury.marc.moodtracker.view.SadFragment;
import com.fleury.marc.moodtracker.view.SuperHappyFragment;

import java.util.Calendar;

public class MainActivity extends FragmentActivity {

    private SharedPreferences mPreferences;
    private Calendar mCalendar = Calendar.getInstance();
    private int dayOfYear = mCalendar.get(Calendar.DAY_OF_YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getPreferences(MODE_PRIVATE);

        ViewPager pager = findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(3);

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


    private void nextDay() {

        if (mPreferences.getInt("date", 0) == 0) { // If "date" == 0 : first launch
            mPreferences.edit().putInt("date", dayOfYear).apply();
        } else {
            mPreferences.edit().putInt("currentDate", dayOfYear).apply();
        }

        if (mPreferences.getInt("date", 0) < mPreferences.getInt("currentDay", 0)) { // If it's next day :
            mPreferences.edit().remove(String.valueOf(dayOfYear - 8) + " mood").apply();
            mPreferences.edit().remove(String.valueOf(dayOfYear - 8) + " comment").apply();
            mPreferences.edit().putInt("date", mPreferences.getInt("currentDate", 0)).apply();
            mPreferences.edit().remove("currentDate").apply();
        }
    }
}
