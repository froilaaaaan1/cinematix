package com.example.movies_ticketing_aquino_villaester_edp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        Intent receiver = getIntent();
        Intent backToHome = new Intent(ReceiptActivity.this, MovieLists.class);
        Button backToHomeButton = findViewById(R.id.backToHome);
        String name = receiver.getStringExtra("fullname");
        String seats_count = receiver.getStringExtra("seat_count");
        String price = receiver.getStringExtra("price");
        String title = receiver.getStringExtra("title");

        backToHomeButton.setOnClickListener(v -> { startActivity(backToHome); });
    }
}