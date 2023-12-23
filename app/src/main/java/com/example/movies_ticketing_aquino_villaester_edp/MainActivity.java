package com.example.movies_ticketing_aquino_villaester_edp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            getWindow().setDecorFitsSystemWindows(false);
        else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }
        getWindow().setNavigationBarColor(Color.TRANSPARENT);

        final Button getStarted = findViewById(R.id.getStartedButton);
        final Button signUpButton = findViewById(R.id.signUpButton);
        final Button loginButton = findViewById(R.id.loginButton);
        final EditText usernameLogin = findViewById(R.id.usernameFieldLogin);
        final EditText passwordLogin = findViewById(R.id.passwordFieldLogin);
        final ViewFlipper onboardingFlipper = findViewById(R.id.onboardingFlipper);
        final Intent intentObject = new Intent(MainActivity.this, MovieLists.class);

        Animation slideInRight = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        Animation slideInLeft = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        onboardingFlipper.setOutAnimation(slideInRight);
        onboardingFlipper.setInAnimation(slideInLeft);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            getWindow().setDecorFitsSystemWindows(false);
        else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }

        getStarted.setOnClickListener(e -> onboardingFlipper.showNext());

        signUpButton.setOnClickListener(e -> {
            AlertDialog.Builder alertDialogBuilderObject = new AlertDialog.Builder(this);
            View viewObject = getLayoutInflater().inflate(R.layout.popup_modal, null);
            alertDialogBuilderObject.setView(viewObject);

            EditText nameTextField = viewObject.findViewById(R.id.nameField);
            EditText usernameTextField = viewObject.findViewById(R.id.usernameField);
            EditText passwordTextField = viewObject.findViewById(R.id.passwordField);
            Button okButton = viewObject.findViewById(R.id.okButton);
            AlertDialog dialogObject = alertDialogBuilderObject.create();
            dialogObject.show();

            okButton.setOnClickListener(f -> {
                if (nameTextField.getText().toString().equals("") || usernameTextField.getText().toString().equals("") || passwordTextField.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "All TextField are required.", Toast.LENGTH_LONG).show();
                else {
                    Database databaseHelper = new Database(this);
                    SQLiteDatabase db = databaseHelper.getReadableDatabase();
                    Toast messageToast = new Toast(MainActivity.this);
                    ContentValues values = new ContentValues();
                    values.put("name", nameTextField.getText().toString());
                    values.put("username", usernameTextField.getText().toString());
                    values.put("password", passwordTextField.getText().toString());

                    long newIdRow = db.insert("users", null, values);
                    if (newIdRow == -1) {
                        messageToast.setText(R.string.something_went_wrong);
                        messageToast.show();
                    } else {
                        messageToast.setText(R.string.success_note);
                        messageToast.show();
                    }
                    db.close();
                }
            });
        });

        loginButton.setOnClickListener(e -> {
            Database databaseHelper = new Database(this);
            Toast messageToast = new Toast(MainActivity.this);
            if (usernameLogin.getText().toString().equals("") || passwordLogin.getText().toString().equals(""))
                Toast.makeText(MainActivity.this, "Username and Password field can't be empty.", Toast.LENGTH_LONG).show();
            else {
                if (databaseHelper.checkIfUserExists(usernameLogin.getText().toString(), passwordLogin.getText().toString())) {
                    messageToast.setText(R.string.success_note);
                    messageToast.show();
                    startActivity(intentObject);
                } else {
                    messageToast.setText(R.string.non_existent);
                    messageToast.show();
                }
                databaseHelper.close();
            }
        });
    }
}