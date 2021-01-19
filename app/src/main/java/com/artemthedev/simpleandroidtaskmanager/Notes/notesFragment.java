package com.artemthedev.simpleandroidtaskmanager.Notes;

import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.artemthedev.simpleandroidtaskmanager.DB.NotesDBHelper;
import com.artemthedev.simpleandroidtaskmanager.R;


public class notesFragment extends Fragment {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public boolean isEmpty = true;

    public notesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notes_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            openHelper = new NotesDBHelper(getActivity().getApplicationContext());
            db = openHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM NOTES", null);
        } catch (SQLiteException e) {
            Toast.makeText(getActivity().getApplicationContext(), "Database unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        new DBLoader().doInBackground((Void) null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getChildFragmentManager().getFragments().clear();
        db.close();
    }

    private class DBLoader extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            int notes = 0;
            if (cursor.moveToLast()) {
                while (!cursor.isBeforeFirst()) {
                    String name = cursor.getString(cursor.getColumnIndex("NAME"));
                    String content = cursor.getString(cursor.getColumnIndex("CONTENT"));
                    String date = cursor.getString(cursor.getColumnIndex("DATE"));
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));

                    displayFragment(name, content, date, id);
                    cursor.moveToPrevious();
                    notes++;
                    isEmpty = false;
                }
            } else {
                getChildFragmentManager().beginTransaction()
                        .add(R.id.base, emptyDBMessageFragment.class, null)
                        .addToBackStack(null)
                        .commit();
            }
            return null;
        }
    }

    private void displayFragment(String name, String content, String date, int id) {

        notePreviewFragment fragment = new notePreviewFragment(name, content, date, id);
        getChildFragmentManager().beginTransaction()
                .add(R.id.base, fragment, null)
                .addToBackStack(null)
                .commit();
    }
}
