package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    TextView txtViewPoll, txtViewAnswers;
    EditText editTxtPoll, editTxtAns1, editTxtAns2, editTxtAns3, editTxtAns4, editTxtAns5, editTxtTime;
    Button buttonAddPoll, buttonAdd, buttonPrev, buttonLogOut;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        txtViewPoll = findViewById(R.id.txtViewPoll);
        txtViewAnswers = findViewById(R.id.txtViewAnswers);
        editTxtPoll = findViewById(R.id.editTxtPoll);
        editTxtAns1 = findViewById(R.id.editTxtAns1);
        editTxtAns2 = findViewById(R.id.editTxtAns2);
        editTxtAns3 = findViewById(R.id.editTxtAns3);
        editTxtAns4 = findViewById(R.id.editTxtAns4);
        editTxtAns5 = findViewById(R.id.editTxtAns5);
        editTxtTime = findViewById(R.id.editTxtTime);
        buttonPrev = findViewById(R.id.buttonPrev);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonLogOut = findViewById(R.id.buttonLogOut);
        buttonAddPoll = findViewById(R.id.buttonAddPoll);
        DB = new DBHelper(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            private int i = 0;
            @Override
            public void onClick(View view) {
                i = i + 1;
                if(i == 1){
                    editTxtAns4.setVisibility(View.VISIBLE);
                }else if(i == 2){
                    editTxtAns5.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(AdminActivity.this, "Ne e mozhno dodavanje na ushte eden odgovor", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonAddPoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poll = editTxtPoll.getText().toString();
                String ans1 = editTxtAns1.getText().toString();
                String ans2 = editTxtAns2.getText().toString();
                String ans3 = editTxtAns3.getText().toString();
                String ans4 = editTxtAns4.getText().toString();
                String ans5 = editTxtAns5.getText().toString();
                String vreme= editTxtTime.getText().toString();
                boolean insert = DB.insertPoll(poll, ans1, ans2, ans3, ans4, ans5, vreme);
                if(insert == true){
                    Toast.makeText(AdminActivity.this, "Uspeshno vnesen poll", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AdminActivity.this, "Neuspeshno vnesen poll", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), PrevActivity.class);
                startActivity(intent);
            }
        });

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }


}