package com.example.thestemapp;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User {

    private String user;
    private String mail;
    private int point;

    public User(String name, String email)
    {
        user = name;
        mail = email;
        getPoints();
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

    public void setPoint()
    {

    }

    public String getUser() {
        return user;
    }

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
