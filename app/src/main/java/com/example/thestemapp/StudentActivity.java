package com.example.thestemapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity {

    private Button Events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Events = (Button) findViewById(R.id.Event);

        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEventList();
            }
        });
    }

    public void startEventList()
    {
        Intent intent = new Intent(this, EventListActivity.class);
        startActivity(intent);
    }
}
