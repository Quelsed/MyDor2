package com.example.dormitory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserGetStatusActivity extends AppCompatActivity {

    private Button gotonotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_get_status);
        gotonotes = findViewById(R.id.gotonotes);
        gotonotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserGetStatusActivity.this, MyNotesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}