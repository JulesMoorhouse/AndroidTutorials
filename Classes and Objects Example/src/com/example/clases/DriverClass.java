package com.example.clases;

public class DriverClass
{
    public static void main(String[] args)
    {
        House house = new House(10, 2, "Tiles", "Brick");
        house.setNrOfDoors(3);
        house.setTypeOfWall("Plaster");
        System.out.println("House:");
        System.out.println(" Nr of Windows: " + house.getNrOfWindows());
        System.out.println(" Nr of Doors: " + house.getNrOfDoors());
        System.out.println(" Type of Roof: " + house.getTypeOfRoof());
        System.out.println(" Type of Wall: " + house.getTypeOfWall());
    }
}
