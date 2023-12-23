package com.example.movies_ticketing_aquino_villaester_edp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            getWindow().setDecorFitsSystemWindows(false);
        else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
        Intent intentObjectReceiver = getIntent();
        String seat = String.valueOf(intentObjectReceiver.getIntExtra("seat_count", 0));
        String price = String.valueOf(intentObjectReceiver.getIntExtra("price", 0));
        String titleYear = intentObjectReceiver.getStringExtra("title");
        String ticketCount = String.valueOf(intentObjectReceiver.getIntExtra("ticket_count", 0));
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

        priceEditText.setText(String.format("Price: %s", price));
        movieNameEditText.setText(String.format("Movie Name: %s", titleYear));
        seatsEditText.setText(String.format("Seat Count: %s", seat));
        ticketNumberEditText.setText(String.format("Ticket Count: %s", ticketCount));

        buyButton.setOnClickListener(e -> {
            AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(FormActivity.this);
            confirmationDialog.setMessage("Make sure the information are all correct.")
                    .setNegativeButton("Check it again", (dialog, which) -> Toast.makeText(FormActivity.this, "Okay, please check the information carefully.", Toast.LENGTH_LONG).show())
                    .setPositiveButton("Please proceed.", (dialog, which) -> {
                        if (fullName.getText().toString().equals(""))
                            Toast.makeText(FormActivity.this, "Oops, you missed something.", Toast.LENGTH_SHORT).show();
                        else {
                            Intent goToReceiptIntent = new Intent(FormActivity.this, ReceiptActivity.class);
                            goToReceiptIntent.putExtra("seat_count", Integer.valueOf(seat));
                            startActivity(goToReceiptIntent);
                        }
                    }).show();
        });
    }
}