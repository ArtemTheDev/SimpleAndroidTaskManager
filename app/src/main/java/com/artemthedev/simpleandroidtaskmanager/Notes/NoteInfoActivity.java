package com.artemthedev.simpleandroidtaskmanager.Notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.artemthedev.simpleandroidtaskmanager.DB.NotesDBHelper;
import com.artemthedev.simpleandroidtaskmanager.R;

public class NoteInfoActivity extends AppCompatActivity {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    String id;
    TextView note_name, note_content, note_date;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_info_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_info);

        note_name = findViewById(R.id.note_name);
        note_content = findViewById(R.id.note_content);
        note_date = findViewById(R.id.note_date);
        
        displayNoteInfo();
        cursor.close();
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayNoteInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != R.id.menu_editButton) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void displayNoteInfo() {
        try {
            openHelper = new NotesDBHelper(this);
            db = openHelper.getWritableDatabase();
            id = getIntent().getExtras().get("id").toString();
            cursor = db.query("NOTES",
                    new String[]{"NAME", "CONTENT", "DATE"},
                    "_id = ?",
                    new String[]{id},
                    null, null, null
            );
        } catch (SQLiteException e) {
            Toast.makeText(this, R.string.db_unavailable, Toast.LENGTH_SHORT).show();
        }

        if (cursor.moveToFirst()) {
            note_name.setText(cursor.getString(0));
            note_content.setText(cursor.getString(1));
            note_date.setText(cursor.getString(2));
        }

        try {
            getActionBar().setTitle(cursor.getString(0));
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            getSupportActionBar().setTitle(cursor.getString(0));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void editNote(MenuItem item) {
        Intent intent = new Intent(this, ChangeNoteActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void deleteNote(MenuItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.erasing_one_note_warning)
                .setTitle(R.string.warning);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            String note_id = id;
            public void onClick(DialogInterface dialog, int id) {
                openHelper = new NotesDBHelper(NoteInfoActivity.this);
                db = openHelper.getReadableDatabase();
                db.execSQL("delete from " + "NOTES" + " Where _id = " + note_id);
                db.close();
                Toast.makeText(NoteInfoActivity.this, R.string.db_note_cleaned, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {}
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}