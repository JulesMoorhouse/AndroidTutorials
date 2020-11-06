package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
{
    private ArrayList<Person> people;
    ItemClicked activity;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }

    public PersonAdapter(Context context, ArrayList<Person> list)
    {
        people = list;
        activity = (ItemClicked) context;
    }

    // Create the cell / row
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivPref;
        TextView tvName, tvSurname;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvSurname = itemView.findViewById(R.id.tvSurname);
            ivPref = itemView.findViewById(R.id.ivPref);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    activity.onItemClicked(people.indexOf((Person) v.getTag()));
                }
            });
        }
    }

    // Set connection between view and layout
    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);

        return new ViewHolder(v);
    }

    // Bind the row items with data
    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position)
    {
        holder.itemView.setTag(people.get(position));

        holder.tvName.setText(people.get(position).getName());
        holder.tvSurname.setText(people.get(position).getSurname());

        if (people.get(position).getPreference().equals("bus"))
        {
            holder.ivPref.setImageResource(R.drawable.bus);
        }
        else
        {
            holder.ivPref.setImageResource(R.drawable.plane);
        }
    }

    @Override
    public int getItemCount()
    {
        return people.size();
    }
}
