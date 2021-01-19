package com.artemthedev.simpleandroidtaskmanager.Settings;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

import com.artemthedev.simpleandroidtaskmanager.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}
