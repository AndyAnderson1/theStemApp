package com.example.thestemapp;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User {

    private String user;
    private int point;

    public User(String name)
    {
        user = name;
        getPoints();
    }

    public void getPoints()
    {
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fb.getReference("Users/" + user + "/Points");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        point = dataSnapshot.getValue(Integer.class);
                    } catch (NullPointerException e) {
                        System.out.println(":(");
                        point = 0;
                        setPoints();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
    }

    //Overwrites a new point value into the database for a user
    public void setPoints()
    {
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference db = fb.getReference("Users/" + user + "/Points");
        db.setValue(point);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void increasePoints(int i)
    {
        final int j = i;
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fb.getReference("Users/" + user + "/Points");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    point = dataSnapshot.getValue(Integer.class);
                    point += j;
                    setPoints();
                } catch (NullPointerException e) {
                    System.out.println(":(");
                    point = 0;
                    setPoints();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public int pointValue()
    {
        getPoints();
        return point;
    }
}
