package com.examples.methods;

public class Methods
{
    public static void main(String[] args)
    {
        // A void method is one that simple performs and task and then terminates

        //System.out.println("This is a void method!");

        // A value-returning method not only performs a task,
        // but also sends a value back to the code that called it

        //int number = Integer.parseInt("700");

        //printAverage(20, 30);

        //double average = getAverage(30,30);
        //System.out.println("Average: " + average);

        //String fullName = fullName("John", "Rambo");
        //System.out.println("Full Name: " + fullName);

        int value = 10;

        if (isValid(value))
        {
            System.out.println("The value is in range!");
        }
        else
        {
            System.out.println("The value is not in range!");
        }
    }

    /**
     * This method prints the average of two numbers
     * @param val1 The first value
     * @param val2 The second value
     */
    public static void printAverage(int val1, int val2)
    {
        double average = (val1 + val2) / 2.0;
        System.out.println("Average: " + average);
    }

    /**
     * This method calculates the average and returns it
     * @param val1 The first value
     * @param val2 The second value
     * @return The average of the 2 values
     */
    public static double getAverage(int val1, int val2)
    {
        double average = (val1 + val2) / 2.0;
        return  average;
    }

    public static String fullName(String name, String surname)
    {
        return name + " " + surname;
    }

    // Range between 1 and 100
    public static boolean isValid(int number)
    {
//        boolean status;
//
//        if (number >= 1 && number <= 100)
//        {
//            status = true;
//        }
//        else
//        {
//            status = false;
//        }
//
//        return status;

        return (number >= 1 && number <= 100);
    }
}
