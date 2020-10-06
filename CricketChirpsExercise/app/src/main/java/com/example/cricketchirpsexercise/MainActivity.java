package com.example.cricketchirpsexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView lblCalculation;
    EditText txtChirps;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblCalculation = (TextView)findViewById(R.id.lblCalculation);
        lblCalculation.setVisibility(View.GONE);
        txtChirps = (EditText)findViewById(R.id.txtChirps);
    }

    public void calcTemp(View view)
    {
        if (!txtChirps.getText().toString().isEmpty())
        {
            int chirps = Integer.parseInt(txtChirps.getText().toString());
            int temp = (chirps / 3) + 4;
            String sentence = getString(R.string.lblApproxTemp, temp);
            lblCalculation.setText(sentence);
            lblCalculation.setVisibility(View.VISIBLE);
        }
    }
}