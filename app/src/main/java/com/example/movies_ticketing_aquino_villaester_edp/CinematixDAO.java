package com.example.movies_ticketing_aquino_villaester_edp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CinematixDAO {
    private final Database databaseHelper;

    public CinematixDAO (Context context) {
        databaseHelper = new Database(context);
    }

    public void openConnection() throws SQLException {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public void closeConnection() {
        databaseHelper.close();
    }

    public int insertBooking(int movieID, String[] seats) {
        // TODO
        return 1;
    }
}