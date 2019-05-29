package com.example.thestemapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventActivity extends AppCompatActivity {

    private Event evt;

    RecyclerView recycleView;
    StudentAdapter studentAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        evt = TeacherActivity.currEvent;

        recycleView = (RecyclerView) findViewById(R.id.students);
        studentAdapt = new StudentAdapter(evt.getUsers());

    }
}
