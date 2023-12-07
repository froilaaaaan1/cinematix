package com.example.movies_ticketing_aquino_villaester_edp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cinematix";
    private static final int DATABASE_VER = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String CREATE_TABLE_SQL_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_NAME + " TEXT, " +
            COLUMN_USERNAME + " INTEGER, " +
            COLUMN_PASSWORD + " TEXT )";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    public boolean checkIfUserExists(String username, String password) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        Cursor cursorObject = sqldb.query(
                "users",
                null,
                "username = ? AND password = ?",
                new String[]{username, password},
                null,
                null,
                null
        );

        boolean itExists = cursorObject.moveToFirst();
        cursorObject.close();
        sqldb.close();
        return itExists;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL_QUERY);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}