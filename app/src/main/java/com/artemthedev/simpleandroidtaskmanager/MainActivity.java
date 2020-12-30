package com.artemthedev.simpleandroidtaskmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private notesFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportFragmentManager().getFragments().size() == 0) displayNotes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void displayNotes() {
        fragment = new notesFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.base, fragment, null)
                .addToBackStack(null)
                .commit();
    }

    public void add (MenuItem item) {
        Intent intent = new Intent(this, NewNoteActivity.class);
        startActivity(intent);
    }

    public void clearDB(MenuItem item) {
        openHelper = new NotesDBHelper(this);
        db = openHelper.getReadableDatabase();
        db.delete("NOTES", null, null);
        displayNotes();
    }
}