package com.artemthedev.simpleandroidtaskmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.Inflater;

public class NewNoteActivity extends AppCompatActivity {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;

    EditText nameInput, textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        try {
            getActionBar().setTitle(R.string.new_note);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            getSupportActionBar().setTitle(R.string.new_note);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        nameInput = findViewById(R.id.note_name_input);
        textInput = findViewById(R.id.note_text_input);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.done) onBackPressed();
        else createNewNote();
        return super.onOptionsItemSelected(item);
    }

    private void createNewNote() {
        String name = nameInput.getText().toString(),
                content = textInput.getText().toString();

        try {
            openHelper = new NotesDBHelper(this);
            db = openHelper.getWritableDatabase();

            ContentValues noteValues = new ContentValues();
            noteValues.put("NAME", name);
            noteValues.put("CONTENT", content);
            noteValues.put("DATE", "2020-12-30-02-20-03");
            db.insert("NOTES", null, noteValues);
            Toast.makeText(this, "Запись сохранена!", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }

    }
}