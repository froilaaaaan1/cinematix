package com.example.movies_ticketing_aquino_villaester_edp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GuidelinesNotesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidelines_notes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            getWindow().setDecorFitsSystemWindows(false);
        else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
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

        confirmationButton.setOnClickListener(v -> {
            if (guideLinesOneCheckbox.isChecked() && guideLinesTwoCheckbox.isChecked() && guideLinesThreeCheckbox.isChecked() && guideLinesFourCheckbox.isChecked() && guideLinesFiveCheckbox.isChecked() && guideLinesSixCheckbox.isChecked()) {
                goToConfirmation.putExtra("price", seatInformation.getIntExtra("price", 0));
                goToConfirmation.putExtra("title", seatInformation.getStringExtra("title"));
                goToConfirmation.putExtra("seat_count", seatInformation.getIntExtra("seat_count", 0));
                goToConfirmation.putExtra("ticket_count", seatInformation.getIntExtra("ticket_count", 0));
                goToConfirmation.putExtra("director", seatInformation.getStringExtra("director"));
                goToConfirmation.putExtra("runtime", seatInformation.getStringExtra("runtime"));
                goToConfirmation.putExtra("year", seatInformation.getStringExtra("year"));
                startActivity(goToConfirmation);
            } else {
                Toast.makeText(
                        GuidelinesNotesActivity.this,
                                "Make sure you've read all guidelines and notes.",
                                Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}