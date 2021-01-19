package com.artemthedev.simpleandroidtaskmanager.Notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.artemthedev.simpleandroidtaskmanager.R;

public class emptyDBMessageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.empty_db_message, container, false);
    }
}
