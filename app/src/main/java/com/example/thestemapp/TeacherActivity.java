package com.example.thestemapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class TeacherActivity extends AppCompatActivity {

    Button Create;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        Create = (Button) findViewById(R.id.event);
        recyclerView = (RecyclerView) findViewById(R.id.recView);

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create Event Activity
            }
        });
    }
}