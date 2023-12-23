package com.example.movies_ticketing_aquino_villaester_edp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            getWindow().setDecorFitsSystemWindows(false);
        else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }
        getWindow().setNavigationBarColor(Color.TRANSPARENT);
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