package com.artemthedev.simpleandroidtaskmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class notePreviewFragment extends Fragment {
    private TextView name_view, text_view, date_view;
    String name, content, date;

    public notePreviewFragment (String name, String content, String date) {
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public notePreviewFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_preview_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        View v = getView();

        name_view = v.findViewById(R.id.note_name);
        text_view = v.findViewById(R.id.note_text);
        date_view = v.findViewById(R.id.note_date);

        name_view.setText(this.name);
        text_view.setText(this.content);
        date_view.setText(this.date);
    }
}
