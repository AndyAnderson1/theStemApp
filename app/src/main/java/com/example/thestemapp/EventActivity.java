package com.example.thestemapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventActivity extends AppCompatActivity {

    private Event evt;

    RecyclerView recycleView;
    StudentAdapter studentAdapt;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        evt = TeacherActivity.currEvent;

        fab = (FloatingActionButton) findViewById(R.id.backEvent);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToEvents();
            }
        });

        recycleView = (RecyclerView) findViewById(R.id.students);
        studentAdapt = new StudentAdapter(evt.getUsers());
        recycleView.setAdapter(studentAdapt);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Events/"+MainActivity.getCurrentTeacher().getUser());

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                studentAdapt.notifyItemRangeChanged(0, evt.getUsers().size()+1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        studentAdapt.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                confirmDelete(pos);
            }
        });
    }

    public void confirmDelete(final int pos)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(evt.getTitle());
        builder.setMessage("Are you sure you want to remove this student?");
        builder.setPositiveButton("Remove",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        evt.removeUser(evt.getMentor(), TeacherActivity.viewPos, pos);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void backToEvents()
    {
        Intent intent = new Intent(this, TeacherActivity.class);
        startActivity(intent);
    }
}
