package com.example.movies_ticketing_aquino_villaester_edp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Intent intentObjectReceiver = getIntent();

        String seat = intentObjectReceiver.getStringExtra("seat");
        String price = intentObjectReceiver.getStringExtra("price");
        String titleYear = intentObjectReceiver.getStringExtra("title");
        EditText priceEditText = findViewById(R.id.Price);
        EditText movieNameEditText = findViewById(R.id.movieNameEditText);
        EditText seatsEditText = findViewById(R.id.numSeats);
        Button buyButton = findViewById(R.id.buyTicketsButton);
        EditText numTicketsEditText = findViewById(R.id.numTicketsEditText);

        priceEditText.setText(price);
        movieNameEditText.setText(String.format("Movie Name: %s", titleYear));
        seatsEditText.setText(seat);

        buyButton.setOnClickListener(e -> {
            AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(FormActivity.this);
            confirmationDialog.setMessage("Make sure the information are all correct.")
                    .setNegativeButton("LMAO, i did something bad.", (dialog, which) -> {
                        Toast.makeText(FormActivity.this, "Okay, please check the information carefully.", Toast.LENGTH_LONG).show();
                    })
                    .setPositiveButton("LOL, proceed.", (dialog, which) -> {
                        Intent goToListIntent = new Intent(FormActivity.this, MovieLists.class);
                        Toast.makeText(FormActivity.this, "Thanks for booking XD.", Toast.LENGTH_LONG).show();
                        Toast.makeText(FormActivity.this, "We're going back to Movie List after a few seconds.", Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(() -> startActivity(goToListIntent), 5000);
                    }).show();

            String movieName = movieNameEditText.getText().toString();
            String numTickets = numTicketsEditText.getText().toString();

            String message = "Purchased " + numTickets + " tickets for " + movieName;
            Toast.makeText(FormActivity.this, message, Toast.LENGTH_SHORT).show();
        });
    }
}