//package com.example.secondtrainep;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class MainActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private ArrayList<Integer> imageList;
//    private ImageAdapter adapter;
//    private Button btnMap;
//
//    private RecyclerView horizontalRecyclerView;
//    private RecyclerView verticalRecyclerView;
//    private HorizontalAdapter horizontalAdapter;
//    private VerticalAdapter verticalAdapter;
//    private ArrayList<Item> horizontalItems;
//    private ArrayList<Item> verticalItems;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        btnMap = findViewById(R.id.btnMap);
//        horizontalRecyclerView = findViewById(R.id.horizontalRecyclerView);
//        verticalRecyclerView = findViewById(R.id.verticalRecyclerView);
//        recyclerView = findViewById(R.id.recyclerView);
//
//        setupHorizontalRecyclerView();
//        setupVerticalRecyclerView();
//        setupImageRecyclerView();
//
//        btnMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMap();
//            }
//        });
//    }
//
//    private void setupHorizontalRecyclerView() {
//        horizontalItems = createHorizontalItems();
//        horizontalAdapter = new HorizontalAdapter(horizontalItems, new HorizontalAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(Item item) {
//                displayVerticalItems(item);
//            }
//        });
//        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        horizontalRecyclerView.setAdapter(horizontalAdapter);
//    }
//
//    private void setupVerticalRecyclerView() {
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
//    }
//
//    private void setupImageRecyclerView() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        imageList = new ArrayList<>();
//        imageList.add(R.drawable.book);
//        imageList.add(R.drawable.controller_image);
//        imageList.add(R.drawable.computer_image);
//        // Add more image resources as needed
//        adapter = new ImageAdapter(imageList);
//        recyclerView.setAdapter(adapter);
//    }
//
//    private ArrayList<Item> createHorizontalItems() {
//        ArrayList<Item> items = new ArrayList<>();
//        items.add(new Item("Grocery", R.drawable.grocery));
//        items.add(new Item("Makeup", R.drawable.makeup));
//        items.add(new Item("Dinner", R.drawable.dinner));
//        items.add(new Item("Lunch", R.drawable.lunch));
//        items.add(new Item("Breakfast", R.drawable.breakfast));
//        items.add(new Item("Clothes", R.drawable.clothes));
//        items.add(new Item("Kids item", R.drawable.kids));
//        items.add(new Item("Men item", R.drawable.men));
//        items.add(new Item("Women", R.drawable.women));
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
//        verticalItems.add(new Item("Apple", R.drawable.apple));
//        verticalItems.add(new Item("Banana", R.drawable.banana));
//        // Add more items as needed
//        verticalAdapter.notifyDataSetChanged();
//    }
//
//    private void incrementQuantity(int position) {
//        Item item = verticalItems.get(position);
//        item.incrementQuantity();
//        verticalAdapter.notifyItemChanged(position);
//    }
//
//    private void decrementQuantity(int position) {
//        Item item = verticalItems.get(position);
//        item.decrementQuantity();
//        verticalAdapter.notifyItemChanged(position);
//    }
//
//    private void openMap() {
//        Uri gmmIntentUri = Uri.parse("geo:0,0?q=restaurants");
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        if (mapIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(mapIntent);
//        }
//    }
//}
