package com.example.thestemapp;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference dr = fb.getReference("Events/"+user);

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("FWEOIFJWEIOFJWEOJFWEIOFJ");
                try
                {
                    GenericTypeIndicator<List<Event>> t = new GenericTypeIndicator <List <Event>>() {};
                    setValue(dataSnapshot.getValue(t));
                    System.out.println(events);
                }
                catch(Exception e)
                {
                    System.out.println("wefoijfaweofjiweiof");
                    events = new ArrayList <>();
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List <Event> getEvents() {
        return events;
    }

    public void setEvents(List <Event> events) {
        this.events = events;
    }
}
