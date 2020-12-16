package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    EditText etNumberTimes;
    Button btnRollDice;
    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumberTimes = (EditText) findViewById(R.id.etNumberTimes);
        tvResults = (TextView) findViewById(R.id.tvResults);
        btnRollDice = (Button) findViewById(R.id.btnRollDice);

        tvResults.setVisibility(View.GONE);

        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int numberOfTimes = Integer.parseInt(etNumberTimes.getText().toString().trim());
                new ProcessDiceInBackground().execute(numberOfTimes);
            }
        });
    }

    public class ProcessDiceInBackground extends AsyncTask<Integer, Integer, String>
    {
        ProgressDialog dialog;

        // Methods occur in this order
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            int max = Integer.parseInt(etNumberTimes.getText().toString().trim());
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(max);
            dialog.show();
        }

        @Override
        protected String doInBackground(Integer... params) {

                int ones = 0, twos = 0, threes = 0, fours = 0, fives = 0, sixes = 0, randomNumber;

                Random random = new Random();

                String results;

                double currentProgress = 0;
                double previousProgress = 0;

                for (int i = 0; i < params[0]; i++)
                {
                    currentProgress = (double)i / params[0];

                    // Update the progress bar every 2%
                    if (currentProgress - previousProgress >= 0.02)
                    {
                        publishProgress(i);
                        previousProgress = currentProgress;
                    }

                    randomNumber = random.nextInt(6) + 1;

                    switch (randomNumber)
                    {
                        case 1 :
                            ones++;
                            break;

                        case 2 :
                            twos++;
                            break;

                        case 3:
                            threes++;
                            break;
                        case 4:
                            fours++;
                            break;
                        case 5:
                            fives++;
                            break;
                        default:
                            sixes++;
                    }
                }

                results = "Results: \n1:" + ones + "\n2:" + twos + "\n3:" + threes + "\n4:" +
                        fours + "\n5:" + fives + "\n5:" + sixes;
            return results;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // Runs in UI Thread
            super.onProgressUpdate(values);

            dialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            // Runs in UI Thread
            super.onPostExecute(s);

            dialog.dismiss();

            tvResults.setText(s);
            tvResults.setVisibility(View.VISIBLE);

            Toast.makeText(MainActivity.this, "Process done!", Toast.LENGTH_SHORT).show();
        }

//        @Override
//        protected void onCancelled(String s) {
//            super.onCancelled(s);
//        }
//
//        @Override
//        protected void onCancelled() {
//            super.onCancelled();
//        }
    }
}