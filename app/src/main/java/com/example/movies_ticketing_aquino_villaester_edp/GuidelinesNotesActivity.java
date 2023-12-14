package com.example.movies_ticketing_aquino_villaester_edp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class GuidelinesNotesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidelines_notes);

        CheckBox guideLinesOneCheckbox, guideLinesTwoCheckbox, guideLinesThreeCheckbox, guideLinesFourCheckbox, guideLinesFiveCheckbox, guideLinesSixCheckbox;
        Button confirmationButton = findViewById(R.id.proceedToConfirmation);
        Intent goToConfirmation = new Intent(GuidelinesNotesActivity.this, FormActivity.class);
        Intent seatInformation = getIntent();
        guideLinesOneCheckbox = findViewById(R.id.guideLineOneChckBox);
        guideLinesTwoCheckbox = findViewById(R.id.guideLineTwoChckBox);
        guideLinesThreeCheckbox = findViewById(R.id.guideLineThreeChckBox);
        guideLinesFourCheckbox = findViewById(R.id.guideLineFourChckBox);
        guideLinesFiveCheckbox = findViewById(R.id.guideLineFiveChckBox);
        guideLinesSixCheckbox = findViewById(R.id.guideLineSixChckBox);

        confirmationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guideLinesOneCheckbox.isChecked() && guideLinesTwoCheckbox.isChecked() && guideLinesThreeCheckbox.isChecked() && guideLinesFourCheckbox.isChecked() && guideLinesFiveCheckbox.isChecked() && guideLinesSixCheckbox.isChecked()) {
                    goToConfirmation.putExtra("name", seatInformation.getStringExtra("seat"));
                    goToConfirmation.putExtra("price", seatInformation.getStringExtra("price"));
                    goToConfirmation.putExtra("title", seatInformation.getStringExtra("title"));
                    startActivity(goToConfirmation);
                } else {
                    Toast.makeText(
                            GuidelinesNotesActivity.this,
                                    "Make sure you've read all guidelines and notes.",
                                    Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}