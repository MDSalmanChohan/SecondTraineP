package com.example.secondtrainep;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    private ArrayList<Item> items;
    private OnItemClickListener listener;
    public int selectedPosition = -1;
    public int lastSelectedPosition = -1;

    public HorizontalAdapter(ArrayList<Item> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Item item = items.get(position);
        holder.itemName.setText(item.getName());
        holder.view.setVisibility(View.GONE);
//        holder.view.setVisibility(View.GONE);
        holder.imageView.setImageResource(item.getImageResource());
//        holder.itemName.setTextColor(Color.parseColor("#FFA500"));
        holder.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.view.setVisibility(View.VISIBLE);
                lastSelectedPosition = selectedPosition;
                selectedPosition = position;
                notifyItemChanged(lastSelectedPosition);
                notifyItemChanged(selectedPosition);
                holder.view.setVisibility(View.VISIBLE);
                listener.onItemClick(item);
            }



        });

        if (selectedPosition == position) {
            holder.itemName.setTextColor(Color.parseColor("#FFA500"));
            holder.view.setVisibility(View.VISIBLE);


        } else {
            holder.itemName.setTextColor(Color.parseColor("#000000"));
            holder.view.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView  itemName;
        View view;



        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            view = itemView.findViewById(R.id.view);


        }
    }
}
