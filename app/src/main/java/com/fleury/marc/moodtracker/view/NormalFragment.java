package com.fleury.marc.moodtracker.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class NormalFragment extends Fragment {


    public static NormalFragment newInstance() {
        return new NormalFragment();
    }

    private Calendar mCalendar = Calendar.getInstance();
    private int dayOfYear = mCalendar.get(Calendar.DAY_OF_YEAR);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_normal, container, false);

        SharedPreferences mPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        ImageView mHistoryButton = v.findViewById(R.id.fragment_normal_hist);
        ImageView mComButton = v.findViewById(R.id.fragment_normal_com);

        mPreferences.edit().putInt(String.valueOf(dayOfYear) + " mood", MoodEnum.NORMAL.getMood()).apply();
        Log.i("MoodTest", String.valueOf(mPreferences.getInt(String.valueOf(dayOfYear) + " mood", 5)));

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


    private void showAlertDialog() {
        FragmentManager fm = getFragmentManager();
        MyDialogFragment alertDialog = MyDialogFragment.newInstance();
        alertDialog.show(fm, "fragment_alert");
    }

}