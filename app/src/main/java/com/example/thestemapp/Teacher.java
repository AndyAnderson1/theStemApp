package com.example.thestemapp;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Teacher
{
    private String user;
    private List <Event> events;

    public Teacher()
    {
        retrieveEvents();
    }

    public Teacher(String name)
    {
        user = name;
        retrieveEvents();
    }

    public void retrieveEvents()
    {
        final FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference dr = fb.getReference("Events/"+MainActivity.getCurrentUser().getUser());

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator <List <Event>> temp = new GenericTypeIndicator <List <Event>>() {};
                if(dataSnapshot.getValue(temp) != null)
                {
                    System.out.println(dataSnapshot.getValue(temp));
                    setValue(dataSnapshot.getValue(temp));
                }
                else
                {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setValue(List<Event> list)
    {
        events.addAll(list);
    }
}
