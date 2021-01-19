package com.artemthedev.simpleandroidtaskmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.artemthedev.simpleandroidtaskmanager.DB.NotesDBHelper;
import com.artemthedev.simpleandroidtaskmanager.Notes.NewNoteActivity;
import com.artemthedev.simpleandroidtaskmanager.Notes.notesFragment;
import com.artemthedev.simpleandroidtaskmanager.Settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private notesFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayNotes();
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        getSupportFragmentManager().beginTransaction().
                remove(fragment).commit();
        super.onSaveInstanceState(state);
    }

    private void displayNotes() {
        fragment = new notesFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.base, fragment, null)
                .commit();
    }

    public void add (MenuItem item) {
        Intent intent = new Intent(this, NewNoteActivity.class);
        startActivity(intent);
    }

    public void clearNotes(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.erasing_all_notes_warning)
                .setTitle(R.string.warning);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (!fragment.isEmpty) {
                    openHelper = new NotesDBHelper(MainActivity.this);
                    db = openHelper.getReadableDatabase();
                    db.delete("NOTES", null, null);
                    Toast.makeText(MainActivity.this, R.string.db_notes_cleaned, Toast.LENGTH_SHORT).show();
                    onResume();
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {}
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void toSettings(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void changeTitle(String title) {
        try {
            getActionBar().setTitle(title);
        } catch (Exception e) {
            getSupportActionBar().setTitle(title);
        }
    }
}