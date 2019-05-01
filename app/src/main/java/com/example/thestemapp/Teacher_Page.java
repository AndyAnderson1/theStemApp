package com.example.thestemapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Teacher_Page extends MainActivity {
 Button CreateEvents;
    Button MyEvents;
     Button Events;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_page2);
        CreateEvents =findViewById(R.id.CreateEvents);
        MyEvents=findViewById(R.id.MyEvents);
        Events=findViewById(R.id.Events);

        CreateEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), CreateForm.class);
             startActivity(i);
            }
        });

        MyEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Teacher_Create_Events.class);
                startActivity(i);
            }
        });

       Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Events.class);
                startActivity(i);
            }
        });
    }
}
