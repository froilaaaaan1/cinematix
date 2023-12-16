package com.example.movies_ticketing_aquino_villaester_edp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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

    private Database databaseHelper = new Database(this);

    private final Set<Integer> selectedSeat = new HashSet<>();

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seats);
        Database dbHelper = new Database(this);
        SQLiteDatabase sqldb = dbHelper.getWritableDatabase();
        Intent intentReceiver = getIntent();
        int imageID = intentReceiver.getIntExtra("imageID", 0);
        Toast.makeText(SeatsActivity.this, intentReceiver.getStringExtra("position"), Toast.LENGTH_LONG).show();
        int seatCount = 30;
        String title = intentReceiver.getStringExtra("title");
        TextView titleYear = findViewById(R.id.titleAndYear);
        TextView selectedSeatTextView = findViewById(R.id.selectedSeatTextView);
        TextView selectedSeatPrice = findViewById(R.id.selectedSeatPrice);
        ImageView chosenMovieImage = findViewById(R.id.banner);
        GridLayout seatGrid = findViewById(R.id.seatGrid);
        TextView yearTextView = findViewById(R.id.year);
        TextView directorTextView = findViewById(R.id.director);
        TextView runTimeTextView = findViewById(R.id.runtime);

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
                    ContentValues values = new ContentValues();
                    values.put("movie_id", intentReceiver.getIntExtra("position", 0));
                    values.put("seat_id", seatID);
                    sqldb.insert("bookings", null, values);

                }
                else {
                    selectedSeat.remove(seatID);
                }
            });

            seatGrid.addView(checkBox);
        }

        Button goToForm = findViewById(R.id.goToForm);
        titleYear.setText(title);
        yearTextView.setText(String.format("Year: %s", intentReceiver.getStringExtra("year")));
        directorTextView.setText(String.format("Director: %s", intentReceiver.getStringExtra("director")));
        runTimeTextView.setText(String.format("Length: %s", intentReceiver.getStringExtra("runtime")));
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

    private boolean isSeatBooked(int movieId, int seatNumber, SQLiteDatabase sqldb) {
        String query = "SELECT * FROM bookings WHERE movie_id = ? AND seat_id = ?;";
        String[] selectionArgs = {String.valueOf(movieId), String.valueOf(seatNumber)};
        Log.i("test", "seatBookFunction");

        try (Cursor cursor = sqldb.rawQuery(query, selectionArgs)) {
            return cursor.getCount() > 0;
        }
    }
    public Set<Integer> getSelectedSeat() {
        return selectedSeat;
    }


}