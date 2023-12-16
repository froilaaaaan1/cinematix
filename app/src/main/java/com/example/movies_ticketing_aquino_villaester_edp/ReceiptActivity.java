package com.example.movies_ticketing_aquino_villaester_edp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        Intent receiver = getIntent();
        int seats_count = receiver.getIntExtra("seat_count", 0);
        LinearLayout container = findViewById(R.id.container);


        for (int i = 0; i < seats_count; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.blue_illustration_cinema_movies_ticket);
            container.addView(imageView);
        }
    }
}