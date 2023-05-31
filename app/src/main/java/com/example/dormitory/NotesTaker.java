package com.example.dormitory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.dormitory.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTaker extends AppCompatActivity {
    private DatabaseReference mDataBase, userDataBase;
    private FirebaseAuth mAuth;

    EditText fio, roomnumber, addcom;
    RadioGroup radioGroup;
    Button save;

    User user;

    NotesFB notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);
        fio = findViewById(R.id.fio);
        roomnumber = findViewById(R.id.roomnumber);
        addcom = findViewById(R.id.addcom);
        radioGroup = findViewById(R.id.radiogroup);
        save = findViewById(R.id.save);
        notes = new NotesFB();





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton=radioGroup.findViewById(radioButtonID);
                int idx=radioGroup.indexOfChild(radioButton);
                RadioButton r = (RadioButton) radioGroup.getChildAt(idx);

                String fio1 = fio.getText().toString();
                String roomnumber1 = roomnumber.getText().toString();
                String addcom1 = addcom.getText().toString();
                String place=r.getText().toString();
                SimpleDateFormat formater = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
                Date date = new Date();

                if (fio1.isEmpty() || roomnumber1.isEmpty() || addcom1.isEmpty() || place.isEmpty()){
                    Toast.makeText(NotesTaker.this,"Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth = FirebaseAuth.getInstance();
                    mDataBase = FirebaseDatabase.getInstance().getReference("NOTES");
                    userDataBase = FirebaseDatabase.getInstance().getReference("USERS").child(mAuth.getCurrentUser().getUid());
                    String id = mDataBase.push().getKey();
                    userDataBase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String email = snapshot.child("email").getValue().toString();

                            NotesFB newUser= new NotesFB(fio1, roomnumber1, addcom1, place, formater.format(date), id, email, "Без комментариев", NotesFB.FixStatus.CREATED);
                            mDataBase.push().setValue(newUser);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });




                    Intent intent = new Intent();
                    setResult(Activity.RESULT_OK, intent);

                    finish();
                }









            }
        });



        Button gotomainactivity = (Button) findViewById(R.id.gotomainactivity);

        View.OnClickListener buttonaddd =new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(NotesTaker.this, MyNotesActivity.class);
                startActivity(intent1);
            }
        };

        gotomainactivity.setOnClickListener(buttonaddd);

    }


}