package com.example.movies_ticketing_aquino_villaester_edp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
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

    private final Database databaseHelper = new Database(this);

    private final Set<Integer> selectedSeat = new HashSet<>();

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            getWindow().setDecorFitsSystemWindows(false);
        else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        Database dbHelper = new Database(this);
        SQLiteDatabase sqldb = dbHelper.getWritableDatabase();
        Intent intentReceiver = getIntent();
        int imageID = intentReceiver.getIntExtra("imageID", 0);
        int seatCount = 30;
        String title = intentReceiver.getStringExtra("title");
        TextView titleYear = findViewById(R.id.titleAndYear);
        ImageView chosenMovieImage = findViewById(R.id.banner);
        ImageView chosenMovieImageBlur = findViewById(R.id.bannerForVignette);
        GridLayout seatGrid = findViewById(R.id.seatGrid);

        for (int i = 0; i < seatCount; i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(String.format("Seat: %d", i));

            checkBox.setId(i);
            if (isSeatBooked(intentReceiver.getIntExtra("position", 0), i + 1, sqldb)) {
                checkBox.setEnabled(false);
                checkBox.setText("Taken");
            }
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                int seatID = buttonView.getId();
                if (isChecked) {
                    selectedSeat.add(seatID);
                }
                else {
                    selectedSeat.remove(seatID);
                }
            });

            seatGrid.addView(checkBox);
        }

        Button goToForm = findViewById(R.id.goToForm);
        titleYear.setText(title);
        chosenMovieImage.setImageResource(imageID);
        chosenMovieImageBlur.setImageResource(imageID);

        goToForm.setOnClickListener(e -> {
            Intent goToGuidelinesIntent = new Intent(SeatsActivity.this, GuidelinesNotesActivity.class);
            if (selectedSeat.isEmpty())
                Toast.makeText(SeatsActivity.this, "Please select a seat", Toast.LENGTH_LONG).show();
            else {
                int price = 0;
                for (Integer seat : selectedSeat) {
                    ContentValues values = new ContentValues();
                    price += 500;
                    values.put("movie_id", intentReceiver.getIntExtra("position", 0));
                    values.put("seat_id", seat + 1);
                    sqldb.insert("bookings", null, values);
                }
                String year = intentReceiver.getStringExtra("year");
                String runtime = intentReceiver.getStringExtra("runtime");
                String director = intentReceiver.getStringExtra("director");
                goToGuidelinesIntent.putExtra("seat_count", selectedSeat.size());
                goToGuidelinesIntent.putExtra("price", price);
                goToGuidelinesIntent.putExtra("ticket_count", selectedSeat.size());
                goToGuidelinesIntent.putExtra("title", titleYear.getText().toString());
                goToGuidelinesIntent.putExtra("director", intentReceiver.getStringExtra("director"));
                goToGuidelinesIntent.putExtra("runtime", intentReceiver.getStringExtra("runtime"));
                goToGuidelinesIntent.putExtra("year", year);
                startActivity(goToGuidelinesIntent);
            }
        });
    }

    private boolean isSeatBooked(int movieId, int seatNumber, SQLiteDatabase sqldb) {
        String query = "SELECT * FROM bookings WHERE movie_id = ? AND seat_id = ?;";
        String[] selectionArgs = {String.valueOf(movieId), String.valueOf(seatNumber)};

        try (Cursor cursor = sqldb.rawQuery(query, selectionArgs)) {
            return cursor.getCount() > 0;
        }
    }
    public Set<Integer> getSelectedSeat() {
        return selectedSeat;
    }


}