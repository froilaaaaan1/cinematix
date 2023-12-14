package com.example.movies_ticketing_aquino_villaester_edp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class SeatsActivity extends AppCompatActivity {

    private final Set<Integer> selectedSeat = new HashSet<>();

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);
        Intent intentReceiver = getIntent();
        int imageID = intentReceiver.getIntExtra("imageID", 0);
        int seatCount = 30;
        String title = intentReceiver.getStringExtra("title");
        TextView titleYear = findViewById(R.id.titleAndYear);
        TextView selectedSeatTextView = findViewById(R.id.selectedSeatTextView);
        TextView selectedSeatPrice = findViewById(R.id.selectedSeatPrice);
        ImageView chosenMovieImage = findViewById(R.id.banner);
        GridLayout seatGrid = findViewById(R.id.seatGrid);
        TextView titleView = findViewById(R.id.title);
        TextView yearTextView = findViewById(R.id.year);
        TextView directorTextView = findViewById(R.id.director);
        TextView runTimeTextView = findViewById(R.id.runtime);

        for (int i = 0; i < seatCount; i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(String.format("Seat: %d", i + 1));
            checkBox.setId(i);
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int seatID = buttonView.getId();
                if (isChecked)
                    selectedSeat.add(seatID);
                else {
                    selectedSeat.remove(seatID);
                }
            });

            seatGrid.addView(checkBox);
        }

        Button goToForm = findViewById(R.id.goToForm);
        titleYear.setText(title);
        titleView.setText(intentReceiver.getStringExtra("title"));
        yearTextView.setText(intentReceiver.getStringExtra("year"));
        directorTextView.setText(intentReceiver.getStringExtra("director"));
        runTimeTextView.setText(intentReceiver.getStringExtra("runtime"));
        chosenMovieImage.setImageResource(imageID);

        goToForm.setOnClickListener(e -> {
            Intent goToGuidelinesIntent = new Intent(SeatsActivity.this, GuidelinesNotesActivity.class);

            goToGuidelinesIntent.putExtra("seat", selectedSeatTextView.getText().toString());
            goToGuidelinesIntent.putExtra("price", selectedSeatPrice.getText().toString());
            goToGuidelinesIntent.putExtra("title", titleYear.getText().toString());
            Toast.makeText(SeatsActivity.this, "Selected Seats: " + getSelectedSeat(), Toast.LENGTH_LONG).show();
            startActivity(goToGuidelinesIntent);
        });
    }

    public Set<Integer> getSelectedSeat() {
        return selectedSeat;
    }


}