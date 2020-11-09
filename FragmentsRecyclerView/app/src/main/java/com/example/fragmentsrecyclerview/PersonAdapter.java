package com.example.fragmentsrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>
{
    private ArrayList<Person> people;

    ItemClicked activity; // Link to main activity

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }

    public PersonAdapter (Context context, ArrayList<Person> list)
    {
        people = list;
        // ListFrag which calls this constructor, ultimately has the main activity
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // itemView is a link to the row layout
            tvName = itemView.findViewById(R.id.tvName);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Person personTag = (Person) v.getTag();
                    int index = people.indexOf(personTag);
                    activity.onItemClicked(index);
                }
            });
        }
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // New row layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position)
    {
        // set tag
        holder.itemView.setTag(people.get(position));
        holder.tvName.setText(people.get(position).getName());
    }

    @Override
    public int getItemCount()
    {
        return people.size();
    }
}
