package com.fleury.marc.moodtracker.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.controller.HistoryActivity;
import com.fleury.marc.moodtracker.model.MoodEnum;

import java.util.Calendar;

public class SadFragment extends Fragment {


    public static SadFragment newInstance() {
        return new SadFragment();
    }

    private SharedPreferences mPreferences;
    private Calendar mCalendar = Calendar.getInstance();
    private int mDay = mCalendar.get(Calendar.YEAR) + mCalendar.get(Calendar.DAY_OF_YEAR);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sad, container, false);

        ImageView mHistoryButton = v.findViewById(R.id.fragment_sad_hist);
        ImageView mComButton = v.findViewById(R.id.fragment_sad_com);

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User clicked the history button
                Intent historyActivityIntent = new Intent(getActivity(), HistoryActivity.class);
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

        return v;
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


    // Method for saving the current mood and producing an associated sound.
    @Override
    public void setUserVisibleHint (boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mPreferences != null) { // If the fragment is visible :
            saveMood(); // The mood is saved ...
            playSound(); // ... And the sound is played.
        }
    }


    // Method for saving mood.
    public void saveMood() {
        mPreferences.edit().putInt(String.valueOf(mDay) + " mood", MoodEnum.SAD.getMood()).apply();
        Log.i("MoodTest", String.valueOf(mPreferences.getInt(String.valueOf(mDay) + " mood", 5)));
    }


    // Method for playing a sound.
    public void playSound() {
        MediaPlayer mSound = MediaPlayer.create(getActivity(), R.raw.g0);
        mSound.start();
    }
}