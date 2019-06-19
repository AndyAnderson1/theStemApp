package com.example.thestemapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinActivity extends AppCompatActivity {

    Event e;
    Button Join;
    TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        e = EventListActivity.currEvent;
        System.out.println(e);

        Name = (TextView) findViewById(R.id.title);
        Join = (Button) findViewById(R.id.join);

        Name.setText(e.getTitle());

        Join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToEvents();
            }
        });
    }

    public void returnToEvents()
    {
        e.addUser(MainActivity.getCurrentUser().getUser());
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Events/" + e.getMentor()+"/"+EventListActivity.currPos);
        db.setValue(e);
        Intent intent = new Intent(this, EventListActivity.class);
        intent.putExtra("Admin", false);
        startActivity(intent);
    }
}
