package com.example.thestemapp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Event
{
    private String title, mentor;
    private int points;
    private List <String> users;

    public Event()
    {
        title = "Fortnite";
        mentor = "Bunty";
        points = 1;
        users = new ArrayList <>();
    }

    public Event(String name, String teacher, int students)
    {
        title = name;
        mentor = teacher;
        points = students;
        users = new ArrayList <>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String toString()
    {
        return title;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List <String> getUsers() {
        return users;
    }

    public void setUsers(List <String> users) {
        this.users = users;
    }

    public void addUser(String name)
    {
        if(!users.contains(name)) {
            users.add(name);
        }
    }

    public void removeUser(String teacher, int event, int name)
    {
        users.remove(name);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Events/"+teacher+"/"+event+"/users");
        db.setValue(users);
    }
}
