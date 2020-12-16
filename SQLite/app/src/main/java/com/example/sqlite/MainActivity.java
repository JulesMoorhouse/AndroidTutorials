package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity
{
    EditText etName, etCell;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etCell = (EditText) findViewById(R.id.etCell);
    }

    public void btnSubmit(View v)
    {
        String name = etName.getText().toString().trim();
        String cell = etCell.getText().toString().trim();

        try
        {
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.createEntry(name, cell);
            db.close();
            Toast.makeText(MainActivity.this, "Successfully saved!", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etCell.setText("");
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnShowData(View v)
    {
        startActivity(new Intent(this, Data.class));
    }

    public void btnEditData(View v)
    {
        try
        {
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.updateEntry("1", "Johan Jurrius", "234554356457");
            db.close();
            Toast.makeText(MainActivity.this, "Successfully updated!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void btnDeleteData(View v)
    {
        try
        {
            ContactsDB db = new ContactsDB(this);
            db.open();
            db.deleteEntry("1");
            db.close();
            Toast.makeText(MainActivity.this, "Successfully deleted!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}