package com.example.intentschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    TextView etName;
    TextView etPhone;
    TextView etWeb;
    TextView etLocation;
    ImageView ivSmile;
    ImageView ivNeutral;
    ImageView ivSad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etWeb = findViewById(R.id.etWeb);
        etLocation = findViewById(R.id.etLocation);
        ivSmile = findViewById(R.id.ivSmile);
        ivNeutral = findViewById(R.id.ivNeutral);
        ivSad = findViewById(R.id.ivSad);

        ivSmile.bringToFront();
        ivNeutral.bringToFront();
        ivSad.bringToFront();

        ivSmile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(R.drawable.smile);
            }
        });

        ivNeutral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(R.drawable.neutral);
            }
        });

        ivSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(R.drawable.sad);
            }
        });
    }

    public void finishActivity(int drawableId)
    {
        if (etName.getText().toString().isEmpty() ||
                etPhone.getText().toString().isEmpty() ||
                etWeb.getText().toString().isEmpty() ||
                etLocation.getText().toString().isEmpty())
        {
            Toast.makeText(DetailActivity.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String web = etWeb.getText().toString().trim();
            String location = etLocation.getText().toString().trim();

            Intent intent = new Intent();

            intent.putExtra("name", name);
            intent.putExtra("phone", phone);
            intent.putExtra("web", web);
            intent.putExtra("location", location);
            intent.putExtra("sentiment", drawableId);

            setResult(RESULT_OK, intent);
            DetailActivity.this.finish();
        }
    }
}