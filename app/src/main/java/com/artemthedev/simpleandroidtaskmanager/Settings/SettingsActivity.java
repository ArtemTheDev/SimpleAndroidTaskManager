package com.artemthedev.simpleandroidtaskmanager.Settings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.artemthedev.simpleandroidtaskmanager.R;

public class SettingsActivity extends AppCompatActivity {


    public SettingsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

        try {
            getActionBar().setTitle(R.string.settings);
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            getSupportActionBar().setTitle(R.string.settings);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}