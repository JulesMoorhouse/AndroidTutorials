package com.example.customlistviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product>
{
    private final Context context;
    private final ArrayList<Product> values;

    public ProductAdapter(Context context, ArrayList<Product> list)
    {
        super(context, R.layout.row_layout, list);
        this.context = context;
        this.values = list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        TextView tvProduct = (TextView) rowView.findViewById(R.id.tvProduct);
        TextView tvPrice = (TextView) rowView.findViewById(R.id.tvPrice);
        TextView tvDescription = (TextView) rowView.findViewById(R.id.tvDescription);

        ImageView ivProduct = (ImageView) rowView.findViewById(R.id.ivProduct);
        ImageView ivSale = (ImageView) rowView.findViewById(R.id.ivSale);

        Product product = values.get(position);

        tvProduct.setText(product.getTitle());
        tvPrice.setText("R" + product.getPrice());
        tvDescription.setText(product.getDescription());

        if(product.getSale())
        {
            ivSale.setImageResource(R.drawable.sale);
        }
        else
        {
            ivSale.setImageResource(R.drawable.best_price);
        }

        String type = product.getType();

        if (type.equals("Laptop"))
        {
            ivProduct.setImageResource(R.drawable.laptop);
        }
        else if (type.equals("Memory"))
        {
            ivProduct.setImageResource(R.drawable.memory);
        }
        else if (type.equals("Screen"))
        {
            ivProduct.setImageResource(R.drawable.screen);
        }
        else
        {
            ivProduct.setImageResource(R.drawable.hdd);
        }

        return rowView;
    }
}
