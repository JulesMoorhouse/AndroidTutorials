package com.example.loops;

public class Loops
{
    public static void main(String[] args)
    {
        // The while loop

        int x = 0; // Loop control variable

        while (x < 5) // pre-test loop
        {
            System.out.println("Hello World!");
            x++;
        }

        System.out.println("");

        // The do-while loop

        int y = 0;

        do {
            System.out.println("Hello World!");
            y++;
        } while(y < 5); // post-test loop

        System.out.println("");

        // The for loop

        for (int i = 0; i < 5; i++) // pre-test loop
        {
            System.out.println("Hello World!");
        }

    }
}
