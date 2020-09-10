package com.example.calculations;

import javax.swing.*;

public class UserControlledLoop
{
    public static void main(String[] args)
    {
        int maxValue;

        String input = JOptionPane.showInputDialog("How hugh should I go to square the nr?");

        maxValue = Integer.parseInt(input);

        System.out.println("Number             Number squared");
        System.out.println("---------------------------------");

        for (int number = 1; number <= maxValue; number++)
        {
            System.out.println(number + "\t\t\t\t\t" + number * number);
        }
    }
}
