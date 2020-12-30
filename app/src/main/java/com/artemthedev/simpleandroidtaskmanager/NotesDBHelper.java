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

    public void createNewNote(String name,String content, NoteDate time) {

    }
}
