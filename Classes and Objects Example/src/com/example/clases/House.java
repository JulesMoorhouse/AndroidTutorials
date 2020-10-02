package com.example.clases;

public class House
{
    private int nrOfWindows;
    private int nrOfDoors;
    private String typeOfRoof;
    private String typeOfWall;

    public House()
    {
        nrOfDoors = 0;
        nrOfWindows = 0;
        typeOfRoof = null;
        typeOfWall = null;
    }

    public House(int nrOfWindows, int nrDoors, String typeOfRoof, String typeOfWall)
    {
        this.nrOfDoors = nrDoors;
        this.nrOfWindows = nrOfWindows;
        this.typeOfRoof = typeOfRoof;
        this.typeOfWall = typeOfWall;
    }

    public int getNrOfWindows()
    {
        return nrOfWindows;
    }

    public int getNrOfDoors()
    {
        return nrOfDoors;
    }

    public String getTypeOfRoof()
    {
        return typeOfRoof;
    }

    public String getTypeOfWall()
    {
        return typeOfWall;
    }

    public void setNrOfWindows(int nrOfWindows)
    {
        this.nrOfWindows = nrOfWindows;
    }

    public void setNrOfDoors(int nrOfDoors)
    {
        this.nrOfDoors = nrOfDoors;
    }

    public void setTypeOfRoof(String typeOfRoof)
    {
        this.typeOfRoof = typeOfRoof;
    }

    public void setTypeOfWall(String typeOfWall) {
        this.typeOfWall = typeOfWall;
    }
}