package com.example.fragmentsrecyclerview;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application
{
    public static ArrayList<Person> people;

    @Override
    public void onCreate()
    {
        super.onCreate();

        people = new ArrayList<Person>();
        people.add(new Person("Chuck Norris", "0989885643"));
        people.add(new Person("John Rambo", "024235345643"));
        people.add(new Person("Nelson Mandela", "08867845643"));
    }
}
