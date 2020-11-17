package com.example.recyclerfragmentschallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CarAdapter.ItemClicked
{
    Button btnCarInfo, btnOwnerInfo;
    ImageView ivMake;
    TextView tvModel, tvOwnerInfo, tvOwnerPhone;
    FragmentManager fragmentManager;
    Fragment buttonFrag, listFrag, carInfoFrag, ownerInfoFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCarInfo = findViewById(R.id.btnCarInfo);
        btnOwnerInfo = findViewById(R.id.btnOwnerInfo);
        ivMake = findViewById(R.id.ivMake);
        tvModel = findViewById(R.id.tvModel);
        tvOwnerInfo = findViewById(R.id.tvOwnerName);
        tvOwnerPhone = findViewById(R.id.tvOwnerPhone);

        fragmentManager = getSupportFragmentManager();

        listFrag = fragmentManager.findFragmentById(R.id.listFrag);
        buttonFrag = fragmentManager.findFragmentById(R.id.buttonFrag);
        carInfoFrag = fragmentManager.findFragmentById(R.id.carInfoFrag);
        ownerInfoFrag = fragmentManager.findFragmentById(R.id.ownerInfoFrag);

        fragmentManager.beginTransaction()
                .show(buttonFrag)
                .show(listFrag)
                .show(carInfoFrag)
                .hide(ownerInfoFrag)
                .commit();

        btnOwnerInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fragmentManager.beginTransaction()
                        .hide(carInfoFrag)
                        .show(ownerInfoFrag)
                        .commit();
            }
        });

        btnCarInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fragmentManager.beginTransaction()
                        .show(carInfoFrag)
                        .hide(ownerInfoFrag)
                        .commit();
            }
        });

        onItemClicked(0);
    }

    @Override
    public void onItemClicked(int index)
    {
        Car car = ApplicationClass.cars.get(index);

        tvOwnerInfo.setText(car.getOwnerName());
        tvModel.setText(car.getModel());
        tvOwnerPhone.setText(car.getOwnerPhone());

        if (car.getMake().equals("Volkswagen"))
        {
            ivMake.setImageResource(R.drawable.acme_cars);
        }
        else if (car.getMake().equals("Nissan"))
        {
            ivMake.setImageResource(R.drawable.circle_cars);
        }
        else
        {
            ivMake.setImageResource(R.drawable.house_cars);
        }
    }
}