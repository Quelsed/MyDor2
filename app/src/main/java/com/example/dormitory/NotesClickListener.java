package com.example.dormitory;

import androidx.cardview.widget.CardView;

public interface NotesClickListener {

    void onClick(NotesFB notesFB);
    void onLongClick(NotesFB notesFB, CardView cardView);


}
