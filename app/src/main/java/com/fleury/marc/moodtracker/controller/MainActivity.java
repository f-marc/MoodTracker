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
import com.fleury.marc.moodtracker.view.MoodFragment;

import java.util.Calendar;

public class MainActivity extends FragmentActivity {

    private SharedPreferences mPreferences;
    private Calendar mCalendar = Calendar.getInstance();
    private int mDay = mCalendar.get(Calendar.YEAR) + mCalendar.get(Calendar.DAY_OF_YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = this.getSharedPreferences("pref", MODE_PRIVATE);

        ViewPager pager = findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        pager.setCurrentItem(3); // We set the default display to "happy".

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
                    return MoodFragment.newInstance(MoodEnum.SAD);
                case 1:
                    return MoodFragment.newInstance(MoodEnum.DISAPPOINTED);
                case 2:
                    return MoodFragment.newInstance(MoodEnum.NORMAL);
                case 3:
                    return MoodFragment.newInstance(MoodEnum.HAPPY);
                case 4:
                    return MoodFragment.newInstance(MoodEnum.SUPERHAPPY);
                default:
                    return MoodFragment.newInstance(MoodEnum.HAPPY);
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

        int date = mPreferences.getInt("date", 0);
        int currentDate = mPreferences.getInt("currentDate", 0);

        if (date < currentDate) { // If it's next day :
            for(int i = 1; i < 359; i++) {
                if (mPreferences.getInt(String.valueOf(mDay - (7 + i)) + " mood", 5) != 5) {
                    mPreferences.edit().remove(String.valueOf(mDay - (7 + i)) + " mood").apply(); // We delete all useless mood's preferences ...
                    mPreferences.edit().remove(String.valueOf(mDay - (7 + i)) + " comment").apply(); // ... And same for comment's ones.
                }
            }
            mPreferences.edit().putInt("date", mDay).apply();
            mPreferences.edit().remove("currentDate").apply();
        }
    }
}
