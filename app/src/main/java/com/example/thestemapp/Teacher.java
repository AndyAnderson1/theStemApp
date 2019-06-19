package com.example.thestemapp;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Teacher
{
    private String user;
    private ArrayList<String> list = new ArrayList <>();
    private  List<Event> eventList = new ArrayList <>();

    public Teacher(String name)
    {
        user = name;

        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference dr = fb.getReference("Teachers");

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try
                {
                    list = dataSnapshot.getValue(ArrayList.class);
                }   catch(Exception e) {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        list.add(user);
        dr.setValue(list);

        //DatabaseReference evtRef = FirebaseDatabase.getInstance().getReference("Events/"+user);

        /*evtRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try
                {
                    GenericTypeIndicator<List<Event>> g = new GenericTypeIndicator <List <Event>>() {};
                    eventList.addAll(dataSnapshot.getValue(g));
                    System.out.println(eventList);
                } catch (Exception e) {
                    System.out.println("HERE");
                    eventList = new ArrayList <>();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }

    public String getUser() {
        return user;
    }

    public void removeEvent(int pos)
    {
        DatabaseReference df = FirebaseDatabase.getInstance().getReference("Events/" + user + "/" + pos);
        df.removeValue();
    }

    public void setUser(String user) {
        this.user = user;
    }
}
