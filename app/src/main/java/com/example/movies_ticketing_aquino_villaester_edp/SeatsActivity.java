package com.example.movies_ticketing_aquino_villaester_edp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SeatsActivity extends AppCompatActivity {
    private RadioGroup selectedRadioGroup;
    private RadioButton selectedRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);
        Intent intentReceiver = getIntent();
        int imageID = intentReceiver.getIntExtra("imageID", 0);
        String title = intentReceiver.getStringExtra("title");
        TextView titleYear = findViewById(R.id.titleAndYear);
        ImageView chosenMovieImage = findViewById(R.id.banner);
        RadioGroup rowOne = findViewById(R.id.seatsRowOne);
        RadioGroup rowTwo = findViewById(R.id.seatsRowTwo);
        RadioGroup rowThree = findViewById(R.id.seatsRowThree);
        RadioGroup rowFour = findViewById(R.id.seatsRowFour);
        titleYear.setText(title);
        chosenMovieImage.setImageResource(imageID);

        validateSeat(rowOne);
        validateSeat(rowTwo);
        validateSeat(rowThree);
        validateSeat(rowFour);
    }

    public void validateSeat(RadioGroup radGroup) {
        TextView selectedSeatTextView = findViewById(R.id.selectedSeatTextView);
        TextView selectedSeatPrice = findViewById(R.id.selectedSeatPrice);
        radGroup.setOnCheckedChangeListener((group, checkedID) -> {
            if (selectedRadioButton != null && selectedRadioGroup != null && group != selectedRadioGroup)
                selectedRadioButton.setChecked(false);

            selectedRadioButton = findViewById(checkedID);
            selectedRadioGroup = group;

            if (selectedRadioButton != null) {
                selectedSeatTextView.setText(String.format("Selected Seat: %s", selectedRadioButton.getText()));
                if (selectedRadioButton.getText().charAt(0) == '1')
                    selectedSeatPrice.setText(R.string.rowOnePrice);
                else if (selectedRadioButton.getText().charAt(0) == '2')
                    selectedSeatPrice.setText(R.string.rowTwoPrice);
                else if (selectedRadioButton.getText().charAt(0) == '3')
                    selectedSeatPrice.setText(R.string.rowThreePrice);
                else
                    selectedSeatPrice.setText(R.string.rowFourPrice);
            }

        });
    }
}