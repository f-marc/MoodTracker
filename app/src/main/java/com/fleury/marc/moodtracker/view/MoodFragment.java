package com.fleury.marc.moodtracker.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.controller.HistoryActivity;
import com.fleury.marc.moodtracker.model.MoodEnum;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoodFragment extends Fragment {


    private static final String EXTRA_MOOD = "mood";

    public static MoodFragment newInstance(MoodEnum mood) {
        MoodFragment f = new MoodFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_MOOD, mood);
        f.setArguments(bundle);
        return f;
    }

    private SharedPreferences mPreferences;
    private Calendar mCalendar = Calendar.getInstance();
    private int mDay = mCalendar.get(Calendar.YEAR) + mCalendar.get(Calendar.DAY_OF_YEAR);

    @BindView(R.id.fragment_mood) RelativeLayout mLayout;
    @BindView(R.id.fragment_mood_view) ImageView mSmiley;
    @BindView(R.id.fragment_mood_hist) ImageView mHistoryButton;
    @BindView(R.id.fragment_mood_com) ImageView mComButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mood, container, false);
        ButterKnife.bind(this, v);

        MoodEnum mood = (MoodEnum) getArguments().getSerializable(EXTRA_MOOD);

        updateLayout(mood, mLayout, mSmiley);
        saveMood(mood, mSmiley);

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User clicked the history button.
                Intent historyActivityIntent = new Intent(getActivity(), HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });

        mComButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User clicked the comment button.
                showAlertDialog();
            }
        });
        return v;
    }


    // Method for displaying the correct components.
    public void updateLayout(MoodEnum mood, RelativeLayout mLayout, ImageView mSmiley) {
        switch(mood) {
            case SAD: // If mood = "Sad" :
                mLayout.setBackgroundColor(getResources().getColor(R.color.faded_red)); // The background is set to red...
                mSmiley.setImageResource(R.drawable.smiley_sad); // ... And the associated image is displayed.
                break;
            case DISAPPOINTED: // If mood = "Disappointed" :
                mLayout.setBackgroundColor(getResources().getColor(R.color.warm_grey)); // The background is set to grey...
                mSmiley.setImageResource(R.drawable.smiley_disappointed); // ... And the associated image is displayed.
                break;
            case NORMAL: // If mood = "Normal" :
                mLayout.setBackgroundColor(getResources().getColor(R.color.cornflower_blue_65)); // The background is set to blue...
                mSmiley.setImageResource(R.drawable.smiley_normal); // ... And the associated image is displayed.
                break;
            case HAPPY: // If mood = "Happy" :
                mLayout.setBackgroundColor(getResources().getColor(R.color.light_sage)); // The background is set to green...
                mSmiley.setImageResource(R.drawable.smiley_happy); // ... And the associated image is displayed.
                break;
            case SUPERHAPPY: // If mood = "SuperHappy" :
                mLayout.setBackgroundColor(getResources().getColor(R.color.banana_yellow)); // The background is set to yellow...
                mSmiley.setImageResource(R.drawable.smiley_super_happy); // ... And the associated image is displayed.
                break;
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
    }


    private void showAlertDialog() {
        FragmentManager fm = getFragmentManager();
        MyDialogFragment alertDialog = MyDialogFragment.newInstance();
        alertDialog.show(fm, "fragment_alert");
    }


    // Method for saving mood.
    public void saveMood(final MoodEnum mood, ImageView mSmiley) {
        mSmiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mSound = new MediaPlayer();
                switch(mood) {
                    case SAD:
                        mPreferences.edit().putInt(String.valueOf(mDay) + " mood", 0).apply();
                        mSound = MediaPlayer.create(getActivity(), R.raw.g0);
                        break;
                    case DISAPPOINTED:
                        mPreferences.edit().putInt(String.valueOf(mDay) + " mood", 1).apply();
                        mSound = MediaPlayer.create(getActivity(), R.raw.g1);
                        break;
                    case NORMAL:
                        mPreferences.edit().putInt(String.valueOf(mDay) + " mood", 2).apply();
                        mSound = MediaPlayer.create(getActivity(), R.raw.g2);
                        break;
                    case HAPPY:
                        mPreferences.edit().putInt(String.valueOf(mDay) + " mood", 3).apply();
                        mSound = MediaPlayer.create(getActivity(), R.raw.g3);
                        break;
                    case SUPERHAPPY:
                        mPreferences.edit().putInt(String.valueOf(mDay) + " mood", 4).apply();
                        mSound = MediaPlayer.create(getActivity(), R.raw.g4);
                        break;
                }
                mSound.start();
            }
        });
    }
}