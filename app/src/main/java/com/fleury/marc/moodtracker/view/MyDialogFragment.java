package com.fleury.marc.moodtracker.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.fleury.marc.moodtracker.R;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class MyDialogFragment extends DialogFragment {

    private SharedPreferences mPreferences;
    private Calendar mCalendar = Calendar.getInstance();
    private int mDay = mCalendar.get(Calendar.YEAR) + mCalendar.get(Calendar.DAY_OF_YEAR);

    public static MyDialogFragment newInstance() {
        return new MyDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mPreferences = getActivity().getSharedPreferences("pref", MODE_PRIVATE);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder
                .setView(R.layout.fragment_dialog)
                .setPositiveButton(R.string.positive_btn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText mEditText = ((AlertDialog) dialog).findViewById(R.id.fragment_dialog_edit);
                        mPreferences.edit().putString(String.valueOf(mDay) + " comment", mEditText.getText().toString()).apply();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.negative_btn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });

        return alertDialogBuilder.create();
    }
}
