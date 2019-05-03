package com.example.thestemapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends AppCompatActivity {

    Button Create;
    RecyclerView recyclerView;

    //List of events from database
    List<Event> events = new ArrayList <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        //Set contents of events from database
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference dr = fb.getReference("Events/"+MainActivity.getCurrentUser().getUser());

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try
                {
                    events = dataSnapshot.getValue(GenericTypeIndicator);
                }
                catch (NullPointerException e){
                    setEvents();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Create = (Button) findViewById(R.id.event);
        recyclerView = (RecyclerView) findViewById(R.id.recView);
        EventAdapter evtAdapter = new EventAdapter(events);
        recyclerView.setAdapter(evtAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        evtAdapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //New Checking Activity
            }
        });

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCreate();
            }
        });
    }

    public void setEvents()
    {
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference dr = fb.getReference("Events/" + MainActivity.getCurrentUser().getUser());
        dr.setValue(events);
    }

    public void startCreate()
    {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }
}