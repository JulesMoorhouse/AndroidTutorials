package com.example.textfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etSurname;
    TextView tvResults;
    ArrayList<Person> people;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        tvResults = (TextView) findViewById(R.id.tvResults);

        people = new ArrayList<Person>();

        loadData();
    }

    public void btnAddData(View v)
    {
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();

        Person person = new Person(name, surname);
        people.add(person);

        setTextToTextView();
    }

    private void setTextToTextView()
    {
        String text = "";

        for (int i = 0; i < people.size(); i++)
        {
            Person person = people.get(i);
            text = text + person.getName() + " " + person.getSurname() + "\n";
        }

        tvResults.setText(text);
    }

    public void loadData()
    {
        people.clear();

        File file = getApplicationContext().getFileStreamPath("Data.txt");
        String lineFromFile;

        if (file.exists())
        {
            try
            {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(openFileInput("Data.txt")));
                while ((lineFromFile = reader.readLine()) != null)
                {
                    StringTokenizer tokens = new StringTokenizer(lineFromFile, ",");
                    Person person = new Person(tokens.nextToken(), tokens.nextToken());
                    people.add(person);
                }

                reader.close();

                setTextToTextView();
            }
            catch (IOException e)
            {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnSaveData(View v)
    {
        try
        {
            FileOutputStream file = openFileOutput("Data.txt", MODE_PRIVATE);
            OutputStreamWriter outputFile = new OutputStreamWriter(file);

            for (int i = 0; i < people.size(); i++)
            {
                Person person = people.get(i);
                outputFile.write(person.getName() + "," + person.getSurname() + "\n");
            }

            outputFile.flush();
            outputFile.close();

            Toast.makeText(MainActivity.this, "Successfully saved!", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}