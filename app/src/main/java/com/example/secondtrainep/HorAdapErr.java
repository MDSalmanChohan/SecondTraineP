//package com.example.secondtrainep;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {
//
//    private ArrayList<Item> items;
//    private OnItemClickListener listener;
//
//    public HorizontalAdapter(ArrayList<Item> items, OnItemClickListener listener) {
//        this.items = items;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Item item = items.get(position);
//        holder.imageView.setImageResource(item.getImageResource());
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick(item);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(Item item);
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.horizontalItemImage);
//        }
//    }
//}
