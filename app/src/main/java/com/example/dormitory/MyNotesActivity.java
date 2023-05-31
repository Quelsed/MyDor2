package com.example.dormitory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.dormitory.Adapter.NotesListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyNotesActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    NotesListAdapter notesListAdapter;
    private DatabaseReference database;

    private FirebaseAuth mAuth;
    Button fab_add;
    List <NotesFB> notes = new ArrayList<>();

    NotesFB selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        Button buttongoback = (Button) findViewById(R.id.buttongoback);
        fab_add = findViewById(R.id.buttonadd);
        recyclerView = findViewById(R.id.recycler_home);
        mAuth = FirebaseAuth.getInstance();

        View.OnClickListener buttonaddd =new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MyNotesActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        };

        buttongoback.setOnClickListener(buttonaddd);

        notesListAdapter = new NotesListAdapter(this, notes, new NotesClickListener() {
            @Override
            public void onClick(NotesFB notesFB) {
                Intent intent = new Intent(MyNotesActivity.this, UserGetStatusActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(NotesFB notesFB, CardView cardView) {
            }
        });
        database = FirebaseDatabase.getInstance().getReference().child("NOTES");
        recyclerView.setAdapter(notesListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notes.clear();
                for (DataSnapshot child : snapshot.getChildren()){
                    NotesFB tmp = child.getValue(NotesFB.class);
                    if (tmp.getEmail().equals( mAuth.getCurrentUser().getEmail())){
                        notes.add(tmp);
                    }
                }
                notesListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyNotesActivity.this, NotesTaker.class);
                startActivityForResult(intent, 101);
            }
        });









}



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101){
            if (resultCode== Activity.RESULT_OK){
                Toast.makeText(MyNotesActivity.this, "Поломка успешно добавлена и отправлена администрации", Toast.LENGTH_SHORT).show();
            }
        }
    }


}