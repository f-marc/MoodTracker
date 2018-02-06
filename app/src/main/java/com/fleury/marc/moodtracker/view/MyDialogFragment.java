package com.fleury.marc.moodtracker.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.model.Mood;

public class MyDialogFragment extends DialogFragment {

    public MyDialogFragment(){

    }

    public static MyDialogFragment newInstance(){
        MyDialogFragment frag = new MyDialogFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        alertDialogBuilder.setView(R.layout.fragment_dialog);
        alertDialogBuilder.setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText = ((AlertDialog) dialog).findViewById(R.id.fragment_dialog_edit);
                Mood mMood = new Mood();
                mMood.setComment(editText.getText().toString());
                Log.i("Test", mMood.getComment()); //Simple test
                dialog.dismiss();
            }
        }); alertDialogBuilder.setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });

        return alertDialogBuilder.create();

    }

}
