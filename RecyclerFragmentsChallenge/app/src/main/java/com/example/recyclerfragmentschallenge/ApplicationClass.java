package com.example.recyclerfragmentschallenge;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application
{
    public static ArrayList<Car> cars;

    @Override
    public void onCreate()
    {
        super.onCreate();

        cars = new ArrayList<Car>();
        cars.add(new Car("Volkswagen", "Polo", "Chuck Norris", "091334356"));
        cars.add(new Car("Mercedes", "E200", "Peter Pollock", "092334356"));
        cars.add(new Car("Nissan", "Almera", "Chris James", "093334356"));
        cars.add(new Car("Mercedes", "E180", "John Rambo", "094334356"));
        cars.add(new Car("Volkswagen", "Kombi", "Nelson Mandela", "095334356"));
        cars.add(new Car("Nissan", "Navara", "Paul Bunting", "096334356"));
    }
}
