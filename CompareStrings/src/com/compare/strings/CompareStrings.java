package com.compare.strings;

import javax.swing.*;

public class CompareStrings
{
    public static void main(String[] args)
    {
        /*
        Please note if you use string literals e.g.
        String name1 = "Peter";
        String name2 = "Peter";
        Peter will use the same address in the string literal pool and will be equal when using ==
        So always use equals or compareTo
         */
        String name1 = JOptionPane.showInputDialog("Please enter name 1: ");
        String name2 = JOptionPane.showInputDialog("Please enter name 2: ");

        // == will compare memory addresses
        //if (name1.equals(name2)) - can use equals or compareTo
        if (name1.compareTo(name2) == 0)
        {
            System.out.println("name 1 is equal to name 2");
        }

        if (name1.compareTo(name2) < 0)
        {
            System.out.println("name 1 is first and name 2 is second in the alphabet");
        }

        if (name1.compareTo(name2) > 0)
        {
            System.out.println("name 2 is first and name 1 is second in the alphabet");
        }

        System.exit(0);
    }
}
