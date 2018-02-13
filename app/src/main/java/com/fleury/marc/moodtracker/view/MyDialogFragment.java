package com.fleury.marc.moodtracker.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;

import com.fleury.marc.moodtracker.R;

public class MyDialogFragment extends DialogFragment {

    SharedPreferences mPreferences;

    public static MyDialogFragment newInstance() {
        return new MyDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        mPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder
                .setView(R.layout.fragment_dialog)
                .setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText mEditText = ((AlertDialog) dialog).findViewById(R.id.fragment_dialog_edit);
                        mPreferences.edit().putString("comment", mEditText.getText().toString()).apply();
                        Log.i("Test", mPreferences.getString("comment", "none"));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });

        return alertDialogBuilder.create();

    }

}
