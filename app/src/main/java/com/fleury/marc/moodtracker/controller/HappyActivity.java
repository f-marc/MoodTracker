package com.fleury.marc.moodtracker.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fleury.marc.moodtracker.R;
import com.fleury.marc.moodtracker.model.OnSwipeListener;

public class HappyActivity extends AppCompatActivity {

    private ImageView mHistoryButton;
    private ImageView mComButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy);

        //On stock l'humeur "Happy"

        mHistoryButton = findViewById(R.id.activity_happy_hist);
        mComButton = findViewById(R.id.activity_happy_com);

        mHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User clicked the button
                Intent historyActivityIntent = new Intent(HappyActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
                finish();
            }
        });

        /*mComButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Commentaire :")

                        //.setEditText ?

                        .setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Save the text then end the activity
                                finish();
                            }
                        })
                        .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();

            }
        });*/



    }
}
