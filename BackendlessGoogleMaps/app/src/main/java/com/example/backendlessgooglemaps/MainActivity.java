package com.example.backendlessgooglemaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.GeoCategory;

public class MainActivity extends AppCompatActivity {

    Button btnFamily, btnPeter, btnThabo, btnNelson, btnSusan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // a-z, A-Z, 0-, _
        Backendless.Geo.addCategory("family", new AsyncCallback<GeoCategory>() {
            @Override
            public void handleResponse(GeoCategory response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(MainActivity.this, "Error: " + fault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnFamily = (Button) findViewById(R.id.btnFamily);
        btnPeter = (Button) findViewById(R.id.btnPeter);
        btnThabo = (Button) findViewById(R.id.btnThabo);
        btnNelson = (Button) findViewById(R.id.btnNelson);
        btnSusan = (Button) findViewById(R.id.btnSusan);

        btnFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowMap("family");
            }
        });

        btnPeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowMap("peter");
            }
        });

        btnThabo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowMap("thabo");
            }
        });

        btnNelson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowMap("nelson");
            }
        });

        btnSusan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ShowMap("susan");
            }
        });
    }

    private void ShowMap(String value)
    {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 0);
        }
        else
        {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("type", value);
            startActivity(intent);
        }
    }
}