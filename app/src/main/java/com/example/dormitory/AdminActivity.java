package com.example.dormitory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dormitory.Adapter.NotesListAdapter;
import com.example.dormitory.Models.User;
import com.example.dormitory.ui.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private List<NotesFB> notesFB;

    private TextView logout;

    NotesListAdapter notesListAdapter;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        logout = findViewById(R.id.logout);
        recyclerView = findViewById(R.id.recycler_home);
        notesFB = new ArrayList<>();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        notesListAdapter = new NotesListAdapter(this, notesFB, new NotesClickListener() {
            @Override
            public void onClick(NotesFB notes) {
                Intent intent = new Intent(AdminActivity.this, AdminSetStatusActivity.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(NotesFB notes, CardView cardView) {

            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("NOTES");
        recyclerView.setAdapter(notesListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notesFB.clear();
                for (DataSnapshot child : snapshot.getChildren()){
                    notesFB.add(child.getValue(NotesFB.class));
                }
                notesListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}