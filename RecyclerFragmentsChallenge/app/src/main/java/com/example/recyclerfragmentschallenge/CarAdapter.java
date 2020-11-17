package com.example.recyclerfragmentschallenge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder>
{
    private ArrayList<Car> cars;

    public CarAdapter(Context context, ArrayList<Car> list)
    {
        cars = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivMake;
        TextView tvModel, tvOwner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMake = itemView.findViewById(R.id.ivMake);
            tvModel= itemView.findViewById(R.id.tvModel);
            tvOwner = itemView.findViewById(R.id.tvOwner);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                }
            });
        }
    }

    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.ViewHolder holder, int position)
    {
        holder.itemView.setTag(cars.get(position));

        holder.tvOwner.setText(cars.get(position).getOwnerName());
        holder.tvModel.setText(cars.get(position).getModel());

        if (cars.get(position).getMake().equals("Volkswagen"))
        {
            holder.ivMake.setImageResource(R.drawable.acme_cars);
        }
        else if (cars.get(position).getMake().equals("Nissan"))
        {
            holder.ivMake.setImageResource(R.drawable.circle_cars);
        }
        else
        {
            holder.ivMake.setImageResource(R.drawable.house_cars);
        }
    }

    @Override
    public int getItemCount()
    {
        return cars.size();
    }
}
