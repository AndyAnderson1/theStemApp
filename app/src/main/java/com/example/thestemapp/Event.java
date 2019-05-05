package com.example.thestemapp;

public class Event
{
    private String title, mentor;
    private int size;

    public Event()
    {
        title = "Fortnite";
        mentor = "Bunty";
        size = 8;
    }

    public Event(String name, String teacher, int students)
    {
        title = name;
        mentor = teacher;
        size = students;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String toString()
    {
        return title;
    }
}
