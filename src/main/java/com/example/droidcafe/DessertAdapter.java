package com.example.droidcafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DessertAdapter extends ArrayAdapter<Dessert> {

    public DessertAdapter(@NonNull Context context, @NonNull List<Dessert> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_dessert, parent, false);
        }

        Dessert dessert = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.imageDessert);
        TextView name = convertView.findViewById(R.id.textName);
        TextView description = convertView.findViewById(R.id.textDescription);

        imageView.setImageResource(dessert.getImageResId());
        name.setText(dessert.getName());
        description.setText(dessert.getDescription());

        return convertView;
    }
}
