package com.example.thestemapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentActivity extends AppCompatActivity {

    private Button Events;
    private TextView Points;
    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Events = (Button) findViewById(R.id.Event);
        Points = (TextView) findViewById(R.id.pointsDisp);

        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Users/"+MainActivity.getCurrentUser().getUser()+"/Points");

        System.out.println(MainActivity.getCurrentUser().getUser());

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try
                {
                    points = dataSnapshot.getValue(Integer.class);
                } catch (Exception e)
                {
                    points = 5;
                }
                Points.setText(String.valueOf(points));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEventList();
            }
        });
    }

    public void startEventList()
    {
        Intent intent = new Intent(this, EventListActivity.class);
        intent.putExtra("Admin", false);
        startActivity(intent);
    }
}
