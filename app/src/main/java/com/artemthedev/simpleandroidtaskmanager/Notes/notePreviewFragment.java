package com.artemthedev.simpleandroidtaskmanager.Notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.artemthedev.simpleandroidtaskmanager.R;

public class notePreviewFragment extends Fragment {
    private TextView name_view, text_view, date_view;
    private CardView card;
    private int dbID;
    String name, content, date;

    public notePreviewFragment (String name, String content, String date, int id) {
        this.name = name;
        this.content = content;
        this.date = date;
        this.dbID = id;
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
        card = v.findViewById(R.id.card_view);

        name_view.setText(this.name);
        text_view.setText(this.content);
        date_view.setText(this.date);

        final int id = this.dbID;
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), NoteInfoActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }
}
