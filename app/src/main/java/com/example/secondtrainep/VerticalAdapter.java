package com.example.secondtrainep;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.ViewHolder> {

    private ArrayList<Item> items;
    private OnQuantityClickListener listener;
    public int value = 0;
    public TextView valueTextView;


    public VerticalAdapter(ArrayList<Item> items, OnQuantityClickListener listener) {
        this.items = items;
        this.listener = listener;

    }

    @NonNull
    @Override
    // these are not null of this product and more maby consumtion in this code.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.imageView.setImageResource(item.getImageResource());
        holder.textView.setText(item.getName());
//        holder.quantityTextView.setText(String.valueOf(item.getQuantity()));
        holder.incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onIncrementClick(position);
                if (item.getQuantity()==0) {
                    holder.quantityTextView.setText("Add");
                } else  {
                    holder.quantityTextView.setText(String.valueOf(item.getQuantity()));

                }

            }
        });
        holder.decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onDecrementClick(position);

                if (item.getQuantity()<=0) {
                    holder.quantityTextView.setText("Add");
                } else {
                    holder.quantityTextView.setText(String.valueOf(item.getQuantity()));

                }

            }

        });



    }


    @Override
    public int getItemCount() {
        return items.size();

    }

    public interface OnQuantityClickListener {
        void onIncrementClick(int position);

        void onDecrementClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView quantityTextView;
        ImageView incrementButton;
        ImageView decrementButton;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImageVertical);
            textView = itemView.findViewById(R.id.itemNameVertical);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            incrementButton = itemView.findViewById(R.id.incrementButton);
            decrementButton = itemView.findViewById(R.id.decrementButton);


        }
    }
   }
