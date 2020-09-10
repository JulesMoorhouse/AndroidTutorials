package com.example.decisions;

public class DecisionStructures
{
    public static void main(String[] args)
    {
        int x = 5;
        int y = 6;
        int p = 7;

        //boolean expression = x == y;
        //boolean expression = x > y && y < p;
        //boolean expression = x < y || y == p;
        boolean expression = !(x < y);

        System.out.println(expression);
    }
}
