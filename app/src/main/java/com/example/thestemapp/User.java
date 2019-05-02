package com.example.thestemapp;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class User {

    private String user;
    private String mail;
    private String userId;
    private int point;

    public User(String name, String email, String uId)
    {
        user = name;
        mail = email;
        getPoints();
        userId=UUID.randomUUID().toString();
    }

    public void getPoints()
    {
        try {
            FirebaseDatabase fb = FirebaseDatabase.getInstance();
            DatabaseReference ref = fb.getReference();

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    point = dataSnapshot.getValue(Integer.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            point = 0;

        }
    }

    public void setPoint(final int x)
    {
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fb.getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                point+= x;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public String getUser() {
        return user;
    }
    public String getUserId(){return userId;}

    public void setUser(String user) {
        this.user = user;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
