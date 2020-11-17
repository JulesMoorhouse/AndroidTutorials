package com.example.recyclerfragmentschallenge;

public class Car
{
    private String make;
    private String model;
    private String ownerName;
    private String ownerPhone;

    public Car(String make, String model, String ownerName, String ownerPhone)
    {
        this.make = make;
        this.model = model;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone()
    {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone)
    {
        this.ownerPhone = ownerPhone;
    }
}
