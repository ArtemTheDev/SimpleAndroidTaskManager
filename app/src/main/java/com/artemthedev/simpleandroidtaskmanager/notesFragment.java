package com.artemthedev.simpleandroidtaskmanager;

import androidx.fragment.app.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class notesFragment extends Fragment {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    public notesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notes_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = getView();

        try {
            openHelper = new NotesDBHelper(getActivity().getApplicationContext());
            db = openHelper.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM NOTES", null);
        } catch (SQLiteException e) {
            Toast.makeText(getActivity().getApplicationContext(), "Database unavailable", Toast.LENGTH_SHORT).show();
        }

        int notes = 0;
        if (cursor.moveToLast()) {
            while (!cursor.isBeforeFirst()) {
                String name = cursor.getString(cursor.getColumnIndex("NAME"));
                String content = cursor.getString(cursor.getColumnIndex("CONTENT"));
                String date = cursor.getString(cursor.getColumnIndex("DATE"));

                displayFragment(name, content, date);
                cursor.moveToPrevious();
                notes++;
            }
        } else {
            getChildFragmentManager().beginTransaction()
                    .add(R.id.base, emptyDBMessageFragment.class, null)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private void displayFragment(String name, String content, String date) {

        notePreviewFragment fragment = new notePreviewFragment(name, content, date);
        getChildFragmentManager().beginTransaction()
                .add(R.id.base, fragment, null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getChildFragmentManager().getFragments().clear();
        db.close();
    }
}
