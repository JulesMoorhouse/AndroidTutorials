package com.example.textfiles;

import java.io.IOException;
import java.io.PrintWriter;

public class WritingToFiles
{
    public static void main(String[] args) throws IOException
    {
        // Create the file and open the file
        // If the file exists it will replace the file
        PrintWriter outputFile = new PrintWriter("OutputFile.txt");

        outputFile.println("This in line 1");
        outputFile.println("This in line 2");
        outputFile.println("This in line 3");
        outputFile.println("This in line 4");

        outputFile.close();
    }
}
