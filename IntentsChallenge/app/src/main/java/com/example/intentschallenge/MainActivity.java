package com.example.intentschallenge;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnCreateContact;
    ImageView ivSentiment;
    ImageView ivPhone;
    ImageView ivWeb;
    ImageView ivLocation;
    LinearLayout layoutMain;
    TextView tvName;

    String locactionUriString;
    String webUriString;
    String phoneUrlString;

    final int ACTIVITY3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateContact = findViewById(R.id.btnCreateContact);
        ivSentiment = findViewById(R.id.ivSentiment);
        ivPhone = findViewById(R.id.ivPhone);
        ivWeb = findViewById(R.id.ivWeb);
        ivLocation = findViewById(R.id.ivLocation);
        layoutMain = findViewById(R.id.layoutMain);
        tvName = findViewById(R.id.tvName);

        layoutMain.setVisibility(View.GONE);

        btnCreateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity();
            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:" + phoneUrlString));
                startActivity(intent);
            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(webUriString));
                startActivity(intent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(locactionUriString));
                startActivity(intent);
            }
        });
    }

    public void openDetailActivity() {
        Intent intent = new Intent(this, DetailActivity.class);
        startActivityForResult(intent, ACTIVITY3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY3)
        {
            if (resultCode == RESULT_OK)
            {
                Integer drawableId = data.getIntExtra("sentiment", 0);
                tvName.setText(data.getStringExtra("name"));
                ivSentiment.setImageResource(drawableId);

                locactionUriString = data.getStringExtra("location");
                webUriString = data.getStringExtra("web");
                phoneUrlString = data.getStringExtra("phone");

                layoutMain.setVisibility(View.VISIBLE);
            }

            if (resultCode == RESULT_CANCELED)
            {
                //tvResults.setText("No data received!");
                layoutMain.setVisibility(View.GONE);
            }
        }
    }
}