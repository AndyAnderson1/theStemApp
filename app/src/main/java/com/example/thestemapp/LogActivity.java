package com.example.thestemapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;

public class LogActivity extends AppCompatActivity {

    Event event;
    Button App;
    List<Event> newList = new ArrayList <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        event = EventListActivity.currEvent;
        App = (Button) findViewById(R.id.app);

        App.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approveEvent();
            }
        });
    }

    public void approveEvent()
    {
        List<String> userList = event.getUsers();

        System.out.println(userList);

        for(String name : userList)
        {
            User user = new User(name);
            user.increasePoints(event.getPoints());
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Events/"+event.getMentor());
        List<Event> data = EventListActivity.events;
        data.remove(EventListActivity.currPos);
        ref.setValue(data);

        Intent intent = new Intent(this, EventListActivity.class);
        intent.putExtra("Admin", true);
        startActivity(intent);
    }
}
