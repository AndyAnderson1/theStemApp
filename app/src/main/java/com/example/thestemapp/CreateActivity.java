package com.example.thestemapp;

import android.content.Intent;
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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    TextView event, teach, points;
    Button create;

    List <Event> tempList = new ArrayList <>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        event = (TextView) findViewById(R.id.title);
        teach = (TextView) findViewById(R.id.mentor);
        points = (TextView) findViewById(R.id.number);
        create = (Button) findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    createEvent();
            }
        });
    }

    public void createEvent()
    {
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference dr = fb.getReference("Events/"+MainActivity.getCurrentTeacher().getUser());

        dr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator <List <Event>> temp = new GenericTypeIndicator <List <Event>>() {};
                if(dataSnapshot.getValue(temp) != null)
                {
                    tempList.addAll(dataSnapshot.getValue(temp));
                    tempList.add(new Event(event.getText().toString(), MainActivity.getCurrentTeacher().getUser(), Integer.valueOf(points.getText().toString())));
                    addEvent(tempList);
                }
                else
                {
                    //System.out.println("EMPTY");
                    tempList.add(new Event(event.getText().toString(), MainActivity.getCurrentTeacher().getUser(), Integer.valueOf(points.getText().toString())));
                    addEvent(tempList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Intent intent = new Intent(this, TeacherActivity.class);
        startActivity(intent);
    }

    public void addEvent(List<Event> list)
    {
        FirebaseDatabase fb = FirebaseDatabase.getInstance();
        DatabaseReference db = fb.getReference("Events/" + MainActivity.getCurrentTeacher().getUser());
        db.setValue(list);
    }
}
