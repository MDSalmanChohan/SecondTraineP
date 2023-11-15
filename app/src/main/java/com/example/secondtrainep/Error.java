//package com.example.secondtrainep;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.widget.TextView;
//import androidx.cardview.widget.CardView;
//import java.util.ArrayList;
//import java.util.List;
////recycler data//
//// map view start
//import android.content.Intent;
//import android.net.Uri;
//// map view start
//
//public class Error extends AppCompatActivity {
//
//    public RecyclerView recyclerView;
//    public ArrayList<Integer> imageList;
//    public ImageAdapter adapter;
//    public ImageView btnMap;
//
//    public RecyclerView horizontalRecyclerView;
//    public RecyclerView verticalRecyclerView;
//    public HorizontalAdapter horizontalAdapter;
//    private VerticalAdapter verticalAdapter;
//    public List<Item> horizontalItems;
//    private List<Item> verticalItems;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        btnMap = findViewById(R.id.btnMap);
//
//        horizontalRecyclerView = findViewById(R.id.horizontalRecyclerView);
//        verticalRecyclerView = findViewById(R.id.verticalRecyclerView);
//
//        // Set up horizontal RecyclerView
//        horizontalItems = createHorizontalItems();
//        horizontalAdapter = new HorizontalAdapter(horizontalItems, new HorizontalAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Item item) {
//                displayVerticalItems(item);
//            }
//        });
//        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        horizontalRecyclerView.setAdapter(horizontalAdapter);
//
//        // Set up vertical RecyclerView
//        verticalItems = new ArrayList<>();
//        verticalAdapter = new VerticalAdapter(verticalItems, new VerticalAdapter.OnQuantityClickListener() {
//            @Override
//            public void onIncrementClick(int position) {
//                incrementQuantity(position);
//            }
//
//            @Override
//            public void onDecrementClick(int position) {
//                decrementQuantity(position);
//            }
//        });
//        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        verticalRecyclerView.setAdapter(verticalAdapter);
//
//
//        btnMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMap();
//            }
//        });
//
//
//
//
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//        imageList = new ArrayList<>();
//        imageList.add(R.drawable.book);
//        imageList.add(R.drawable.controller_image);
//        imageList.add(R.drawable.computer_image);
//        imageList.add(R.drawable.glob);
//        imageList.add(R.drawable.gulab_jamun);
//        imageList.add(R.drawable.computer_image);
//        imageList.add(R.drawable.book);
//        imageList.add(R.drawable.controller_image);
//        imageList.add(R.drawable.computer_image);
//        imageList.add(R.drawable.glob);
//        imageList.add(R.drawable.gulab_jamun);
//        imageList.add(R.drawable.computer_image);
//        imageList.add(R.drawable.book);
//        imageList.add(R.drawable.controller_image);
//        imageList.add(R.drawable.computer_image);
//        imageList.add(R.drawable.glob);
//        imageList.add(R.drawable.gulab_jamun);
//        imageList.add(R.drawable.computer_image);
//        imageList.add(R.drawable.glob);
//        imageList.add(R.drawable.computer_image);
//        imageList.add(R.drawable.book);
//        imageList.add(R.drawable.gulab_jamun);
//        imageList.add(R.drawable.laptop_image);
//        imageList.add(R.drawable.controller_image);
//        imageList.add(R.drawable.gulab_jamun);
//        // Add more image resources as needed
//
//        adapter = new ImageAdapter(imageList);
//        recyclerView.setAdapter(adapter);
//    }
//
//
//
//    ////recycler data code start
//
//    private List<Item> createHorizontalItems() {
//        List<Item> items = new ArrayList<>();
//        items.add(new Item("Grocerry", R.drawable.grocerry));
//        items.add(new Item("Makup", R.drawable.makup));
//        items.add(new Item("Dinner", R.drawable.dinner));
//        items.add(new Item("Lunch", R.drawable.lunch));
//        items.add(new Item("Breakfast", R.drawable.breakfast));
//        items.add(new Item("Clothes ", R.drawable.clothes));
//        items.add(new Item("Kids item", R.drawable.kids));
//        items.add(new Item("Men item", R.drawable.men));
//        items.add(new Item("Women ", R.drawable.women));
//        items.add(new Item("School", R.drawable.school));
//        // Add more items as needed
//        return items;
//    }
//
//    private void displayVerticalItems(Item item) {
//        verticalItems.clear();
//        verticalItems.add(item);
//        // Add additional data to verticalItems based on selected item
//        // For example:
//        verticalItems.add(new Item("Orange", R.drawable.orange));
//        verticalItems.add(new Item("Apple", R.drawable.apples));
//        verticalItems.add(new Item("Banana", R.drawable.apple));
//        verticalItems.add(new Item("Grapes", R.drawable.fraps));
//        verticalItems.add(new Item("PineApples", R.drawable.fruits));
//        verticalItems.add(new Item("Guavava", R.drawable.breakfast));
//        verticalItems.add(new Item("gulabjamun", R.drawable.gulab_jamun));
//        verticalItems.add(new Item("Papaya", R.drawable.appless));
//        verticalItems.add(new Item("Grapes", R.drawable.fraps));
//        verticalItems.add(new Item("PineApples", R.drawable.fruits));
//        verticalItems.add(new Item("Guavava", R.drawable.breakfast));
//        verticalItems.add(new Item("gulabjamun", R.drawable.orange));
//        verticalItems.add(new Item("Papaya", R.drawable.appless));
//
//        verticalAdapter.notifyDataSetChanged();
//
//
//    }
//
//    private void incrementQuantity(int position) {
//        Item item = verticalItems.get(position);
//        int quantity = item.getQuantity();
//        quantity++;
//        item.setQuantity(quantity);
//        verticalAdapter.notifyItemChanged(position);
//    }
//
//    private void decrementQuantity(int position) {
//        Item item = verticalItems.get(position);
//        int quantity = item.getQuantity();
//        if (quantity > 0) {
//            quantity--;
//            item.setQuantity(quantity);
//            verticalAdapter.notifyItemChanged(position);
//        }
//    }
//
//    // Horizontal RecyclerView Adapter
//    private static class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {
//
//        private List<Item> items;
//        private OnItemClickListener onItemClickListener;
//
//        interface OnItemClickListener {
//
//
//            void onItemClick(Item item);
//        }
//
//        HorizontalAdapter(List<Item> items, OnItemClickListener onItemClickListener) {
//            this.items = items;
//            this.onItemClickListener = onItemClickListener;
//        }
//
//        static class ViewHolder extends RecyclerView.ViewHolder {
//            ImageView itemImage;
//            TextView itemName;
//
//            ViewHolder(View itemView) {
//                super(itemView);
////                @NonNull
//                itemImage = itemView.findViewById(R.id.itemImage);
//                itemName = itemView.findViewById(R.id.itemName);
//            }
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, final int position) {
//            final Item item = items.get(position);
//            holder.itemImage.setImageResource(item.getImageResourceId());
//            holder.itemName.setText(item.getName());
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onItemClickListener != null) {
//                        onItemClickListener.onItemClick(item);
//                    }
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return items.size();
//        }
//    }
//
//    // Vertical RecyclerView Adapter
//    private static class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.ViewHolder> {
//
//        private List<Item> items;
//        private OnQuantityClickListener onQuantityClickListener;
//
//
//        //why you did not working on this profile and measerment the function of this role to introduce the projuect file
//        interface OnQuantityClickListener {
//            void onIncrementClick(int position);
//            void onDecrementClick(int position);
//        }
//
//
//        VerticalAdapter(List<Item> items, OnQuantityClickListener onQuantityClickListener) {
//            this.items = items;
//            this.onQuantityClickListener = onQuantityClickListener;
//        }
//
//        static class ViewHolder extends RecyclerView.ViewHolder {
//            //thats are giving the error
//
//            CardView cardView;
//            ImageView itemImageVertical;
//            TextView itemNameVertical;
//            Button incrementButton;
//            TextView quantityTextView;
//            Button decrementButton;
//
//            ViewHolder(View itemView) {
//
//                super(itemView);
//                cardView = itemView.findViewById(R.id.cardView);
//                itemImageVertical = itemView.findViewById(R.id.itemImageVertical);
//                itemNameVertical = itemView.findViewById(R.id.itemNameVertical);
//                incrementButton = itemView.findViewById(R.id.incrementButton);
//                quantityTextView = itemView.findViewById(R.id.quantityTextView);
//                decrementButton = itemView.findViewById(R.id.decrementButton);
//            }
//        }
//
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, final int position) {
//            final Item item = items.get(position);
//            holder.itemImageVertical.setImageResource(item.getImageResourceId());
//            holder.itemNameVertical.setText(item.getName());
//            holder.quantityTextView.setText(String.valueOf(item.getQuantity()));
//
//            holder.incrementButton.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//
//                    if (onQuantityClickListener != null) {
//
//                        onQuantityClickListener.onIncrementClick(position);
//                    }
//                }
//            });
//
//
//            holder.decrementButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (onQuantityClickListener != null) {
//                        onQuantityClickListener.onDecrementClick(position);
//                    }
//                }
//            });
//        }
//
//
//
//        @Override
//        public int getItemCount() {
//            return items.size();
//        }
//    }
//
//    // Item model class
//    private static class Item {
//        private String name;
//        private int imageResourceId;
//        private int quantity;
//
//        Item(String name, int imageResourceId) {
//            this.name = name;
//            this.imageResourceId = imageResourceId;
//            this.quantity = 0;
//        }
//
//        String getName() {
//            return name;
//        }
//
//        int getImageResourceId() {
//            return imageResourceId;
//        }
//
//        int getQuantity() {
//            return quantity;
//        }
//
//        void setQuantity(int quantity) {
//            this.quantity = quantity;
//        }
//    }
//
//    ///recycler dat code end
//    private void openMap() {
//        Uri gmmIntentUri = Uri.parse("geo:0,0?q=X747+365+shiv+park,+Tatiri,+Uttar+Pradesh+250601");
//        Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        startActivity(intent);
//    }
//
//    private class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
//        private ArrayList<Integer> images;
//
//        public ImageAdapter(ArrayList<Integer> images) {
//            this.images = images;
//        }
//
//        @NonNull
//        @Override
//        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
//            return new ImageViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
//            int imageRes = images.get(position);
//            holder.imageView.setImageResource(imageRes);
//        }
//
//        @Override
//        public int getItemCount() {
//            return images.size();
//        }
//
//        public class ImageViewHolder extends RecyclerView.ViewHolder {
//            ImageView imageView;
//
//            public ImageViewHolder(@NonNull View itemView) {
//
//                super(itemView);
//                imageView = itemView.findViewById(R.id.imageView);
//            }
//        }
//    }
//
//}
