package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvWelcome;
    EditText etName;
    Button btnSubmit;

    public static final String MY_PREFERENCES_FILENAME = "com.example.sharedpreferences.Names";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        etName = (EditText) findViewById(R.id.etName);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        SharedPreferences prefs = getSharedPreferences(MY_PREFERENCES_FILENAME, MODE_PRIVATE);
        String name = prefs.getString("user", "");

        setName(name);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString().trim();
                setName(name);

                // Private - only accessible by your app
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFERENCES_FILENAME, MODE_PRIVATE).edit();
                editor.putString("user", name);
                editor.commit();
            }
        });


    }

    public void setName(String name)
    {
        if (!name.isEmpty())
        {
            tvWelcome.setText("Welcome to my app " + name + "!");
        }
    }
}