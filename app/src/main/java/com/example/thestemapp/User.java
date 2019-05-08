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
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference ref = fb.getReference("Users/" + user + "/Points");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try {
                        point = dataSnapshot.getValue(Integer.class);
                        System.out.println(point + " " + user);
                    } catch (NullPointerException e) {
                        point = 0;
                        setPoint("Users/" + user + "/Points");
                        System.out.println("UPOEDUI");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
    }

    //Overwrites a new point value into the database for a user
    public void setPoint(String path)
    {
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference db = fb.getReference(path);
        db.setValue(point);
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

    public void increasePoints()
    {
        point++;
        setPoint("Users/" + user + "/Points");
    }

    public int pointValue()
    {
        getPoints();
        return point;
    }
}
