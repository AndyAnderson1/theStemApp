package com.example.thestemapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    private Button Approve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Approve = (Button) findViewById(R.id.approve);

        Approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startApprove();
            }
        });
    }

    public void startApprove()
    {
        Intent intent = new Intent(this, EventListActivity.class);
        intent.putExtra("Admin", true);
        startActivity(intent);
    }
}
