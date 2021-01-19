package com.example.backendlesstestapp;

import android.renderscript.ScriptIntrinsicYuvToRGB;

public class Person
{
    private String firstName;
    private  String lastName;

    public Person()
    {
        firstName = null;
        lastName = null;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
