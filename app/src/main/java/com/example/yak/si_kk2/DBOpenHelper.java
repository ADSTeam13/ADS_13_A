package com.example.yak.si_kk2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yak on 01/05/2018.
 */

public class DBOpenHelper extends SQLiteOpenHelper {

    private static  final String DATABASE_NAME = "note.db";
    private static  final int DATABASE_VERSION = 1;

    public static final String USER = "user";
    public static final String USER_ID = "_id";
    public static final String USER_NAME = "username";
    public static final String EMAIL = "email";
    public static final String NOTELP = "notelp";
    public static final String COMMUNITY_NAME ="communityname";
    public static final String PASSWORD = "password";
    public static final String USER_MODE = "usermode";
    public static final String NOTE_TEXT ="notetext";
    public static final String NOTE_CREATED ="notecreated";

    private static final String TABLE_CREATE =
            "CREATE TABLE"+ USER +"("+USER_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"+
             USER_NAME +" TEXT,"+ EMAIL+"TEXT,"+ NOTELP +"TEXT,"+
                    COMMUNITY_NAME +" TEXT,"+ PASSWORD+"TEXT,"+ USER_MODE+"TEXT," +
                    NOTE_TEXT +" TEXT,"+ NOTE_CREATED+"TEXT default CURRENT_TIMESTAMP"+")";



    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER);
        onCreate(db);
    }
}
