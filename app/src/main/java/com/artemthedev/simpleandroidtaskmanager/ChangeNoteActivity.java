package com.artemthedev.simpleandroidtaskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class ChangeNoteActivity extends AppCompatActivity {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    EditText et_name, et_content;

    String id;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        id = getIntent().getExtras().get("id").toString();
        et_name = findViewById(R.id.note_name_input);
        et_content = findViewById(R.id.note_text_input);

        try {
            getActionBar().setTitle(R.string.change_note);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            getSupportActionBar().setTitle(R.string.change_note);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        try {
            openHelper = new NotesDBHelper(this);
            db = openHelper.getWritableDatabase();
            cursor = db.query("NOTES",
                    new String[] {"NAME", "CONTENT", "DATE"},
                    "_id = ?",
                    new String[] {id},
                    null, null, null
            );
            if(cursor.moveToFirst()) {
                et_name.setText(cursor.getString(0));
                et_content.setText(cursor.getString(1));
            }

        } catch (SQLiteException e) {
            Toast.makeText(this, R.string.db_unavailable, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.done) onBackPressed();
        else saveNote();
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        try {
            openHelper = new NotesDBHelper(this);
            db = openHelper.getWritableDatabase();
            String newName = String.format("'%s'", et_name.getText().toString()),
                   newContent = String.format("'%s'", et_content.getText().toString());
            db.execSQL("UPDATE " + "NOTES" + " SET NAME = " + newName + "Where _id = " + id);
            db.execSQL("UPDATE " + "NOTES" + " SET CONTENT = " + newContent + "Where _id = " + id);
            Toast.makeText(getApplicationContext(), R.string.note_edited, Toast.LENGTH_LONG).show();
            db.close();
            onBackPressed();

        } catch (SQLiteException e) {
            Toast.makeText(this, R.string.db_unavailable, Toast.LENGTH_SHORT).show();
        }
    }
}