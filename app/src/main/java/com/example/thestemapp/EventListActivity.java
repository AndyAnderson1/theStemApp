package com.example.thestemapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EventListActivity extends AppCompatActivity {

    List<Event> events = new ArrayList <>();

    RecyclerView recyclerView;
    EventAdapter evtAdapter;

    public static Event currEvent;
    public static int currPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        final FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference dr = fb.getReference("Teachers");

        ValueEventListener vl = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<List<String>> t = new GenericTypeIndicator <List <String>>() {};

                for(String x : dataSnapshot.getValue(t))
                {
                    DatabaseReference data = FirebaseDatabase.getInstance().getReference("Events/" + x);

                    data.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            GenericTypeIndicator<List<Event>> t = new GenericTypeIndicator <List <Event>>() {};
                            events.addAll(dataSnapshot.getValue(t));
                            evtAdapter.notifyItemRangeChanged(0, events.size());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        dr.addValueEventListener(vl);

        recyclerView = (RecyclerView) findViewById(R.id.list);
        evtAdapter = new EventAdapter(events);
        recyclerView.setAdapter(evtAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        evtAdapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                currEvent = events.get(pos);
                currPos = pos;
                startJoin();
            }
        });
    }

    public void startJoin()
    {
        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);
    }
}
