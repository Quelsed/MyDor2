package com.example.dormitory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AdminSetStatusActivity extends AppCompatActivity {

    private EditText comm;

    private RadioGroup radioGroup;
    private Button save, gotoAdminactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_set_status);
        comm = findViewById(R.id.comm);
        save = findViewById(R.id.save);
        gotoAdminactivity = findViewById(R.id.gotoAdminActivity);
        radioGroup = findViewById(R.id.radiogroup);

        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton=radioGroup.findViewById(radioButtonID);
        int idx=radioGroup.indexOfChild(radioButton);
        RadioButton r = (RadioButton) radioGroup.getChildAt(idx);

        gotoAdminactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSetStatusActivity.this, AdminActivity.class);
                startActivity(intent);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comm1 = comm.getText().toString();
                String status = r.getText().toString();
            }
        });


    }
}