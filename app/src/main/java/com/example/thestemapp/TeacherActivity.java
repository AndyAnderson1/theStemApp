package com.example.thestemapp;

import android.content.Intent;
import android.provider.ContactsContract;
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
    EventAdapter eventAdapter;

    public static int viewPos;

    List<Event> events = new ArrayList <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        final FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference dr = fb.getReference("Events/"+MainActivity.getCurrentTeacher().getUser());

        ValueEventListener vl = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Event>> t = new GenericTypeIndicator <List <Event>>() {};
                setValue(dataSnapshot.getValue(t));
                eventAdapter.notifyItemRangeChanged(0, events.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        dr.addValueEventListener(vl);

        Create = (Button) findViewById(R.id.event);

        recyclerView = (RecyclerView) findViewById(R.id.recView);
        eventAdapter = new EventAdapter(events);
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventAdapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                viewPos = pos;
                startEvent();
            }
        });

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCreate();
            }
        });
    }

    public void startCreate() {
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    public void startEvent() {
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }

    public void setValue(List<Event> list) {
        events.addAll(list);
    }

    public void updateUI(int end) {
        eventAdapter.notifyItemRangeChanged(0, end);
    }
}