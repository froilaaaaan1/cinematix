package com.example.movies_ticketing_aquino_villaester_edp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        EditText fullName = findViewById(R.id.fullNameEditText);
        EditText movieNameEditText = findViewById(R.id.movieNameEditText);
        EditText seatsEditText = findViewById(R.id.numSeats);
        EditText ticketNumberEditText = findViewById(R.id.numTicketsEditText);
        Button buyButton = findViewById(R.id.buyTicketsButton);
        priceEditText.setFocusable(false);
        movieNameEditText.setFocusable(false);
        ticketNumberEditText.setFocusable(false);
        seatsEditText.setFocusable(false);

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
                        if (fullName.getText().toString().equals(""))
                            Toast.makeText(FormActivity.this, "Oops, you missed something.", Toast.LENGTH_SHORT).show();
                        else {
                            Intent goToReceiptIntent = new Intent(FormActivity.this, ReceiptActivity.class);
                            goToReceiptIntent.putExtra("price", price);
                            goToReceiptIntent.putExtra("title", titleYear);
                            goToReceiptIntent.putExtra("fullname", fullName.getText().toString());
                            goToReceiptIntent.putExtra("seat_count", seatsEditText.getText().toString());
                            startActivity(goToReceiptIntent);
                        }
                    }).show();
        });
    }
}