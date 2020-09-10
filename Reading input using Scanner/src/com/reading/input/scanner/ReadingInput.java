package com.reading.input.scanner;

import java.util.Scanner;

public class ReadingInput
{
    public static void main(String[] args)
    {
        int firstNumber;
        int secondNumber;

        Scanner kb = new Scanner(System.in);

        System.out.println("Please enter the first number: ");
        firstNumber = kb.nextInt();

        System.out.println("Please enter the second number: ");
        secondNumber = kb.nextInt();

        double average = (double)(firstNumber + secondNumber) / 2;

        System.out.println("The average is: " + average);

        String name;
        String surname;

        kb.nextLine(); // consume next line char when using nextInt etc

        System.out.println("Please enter your first name: ");
        name = kb.nextLine();

        System.out.println("Please enter your surname: ");
        surname = kb.nextLine();

        System.out.println(name + " " + surname);
    }
}
