package com.artemthedev.simpleandroidtaskmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "UserNotes";
    private static final int DB_VER = 1;

    public NotesDBHelper (Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NOTES(_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, CONTENT TEXT, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public static String validate(String s) {
        s = validateEnters(s);
        s = validateSpaces(s);
        return s;
    }

    public static String validateSpaces(String s) {
        StringBuilder res = new StringBuilder();
        char c;
        boolean allowed = false;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == ' ') {
                if (allowed) {
                    allowed = false;
                    res.append(c);
                }
            } else {
                allowed = true;
                res.append(c);
            }
        }
        return res.toString();
    }

    public static String validateEnters(String s) {
        StringBuilder res = new StringBuilder();
        char c;
        boolean allowed = false;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '\n') {
                if (allowed) {
                    allowed = false;
                    res.append(c);
                }
            } else {
                allowed = true;
                res.append(c);
            }
        }
        if ( s.charAt(s.length()-1) == '\n' ) res.deleteCharAt(res.length()-1);
        return res.toString();
    }
}
