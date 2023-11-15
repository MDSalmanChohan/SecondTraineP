//package com.example.secondtrainep;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class HomeFragment extends Fragment {
//
//    private RecyclerView horizontalRecyclerView;
//    private RecyclerView verticalRecyclerView;
//    private HorizontalAdapter horizontalAdapter;
//    private VerticalAdapter verticalAdapter;
//    private ArrayList<Item> horizontalItems;
//    private ArrayList<Item> verticalItems;
//    private ArrayList<Integer> imageList;
//    private ImageAdapter adapter;
//    private RecyclerView recyclerView;
//    private Button btnMap;
//
//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView);
//        verticalRecyclerView = view.findViewById(R.id.verticalRecyclerView);
//        recyclerView = view.findViewById(R.id.recyclerView);
//        btnMap = view.findViewById(R.id.btnMap);
//
//
//        setupHorizontalRecyclerView();
//        setupVerticalRecyclerView();
//        setupImageRecyclerView();
//
//
//        btnMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openMap();
//            }
//        });
//    }
//
//    private void setupImageRecyclerView() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
//        imageList = new ArrayList<>();
//
//        imageList.add(R.drawable.bluban);
//        imageList.add(R.drawable.orangebann);
//        imageList.add(R.drawable.greensban);
//        imageList.add(R.drawable.bluban);
//        imageList.add(R.drawable.orangebann);
//        imageList.add(R.drawable.greensban);
//        imageList.add(R.drawable.redbann);
//        imageList.add(R.drawable.bluban);
//        imageList.add(R.drawable.greenss);
//        imageList.add(R.drawable.orangebann);
//        imageList.add(R.drawable.greensban);
//        imageList.add(R.drawable.bluban);
//        imageList.add(R.drawable.redbann);
//        imageList.add(R.drawable.greenss);
//
//        // Add more image resources as needed
//        adapter = new ImageAdapter(imageList);
//        recyclerView.setAdapter(adapter);
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
//        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
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
//        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
//        verticalRecyclerView.setAdapter(verticalAdapter);
//    }
//
//    private ArrayList<Item> createHorizontalItems() {
//        ArrayList<Item> items = new ArrayList<>();
//        items.add(new Item("Grocery", R.drawable.grocerry));
//        items.add(new Item("Makeup", R.drawable.makup));
//        items.add(new Item("Dinner", R.drawable.dinner));
//        items.add(new Item("Lunch", R.drawable.lunch));
//        items.add(new Item("Breakfast", R.drawable.breakfast));
//        items.add(new Item("Clothes", R.drawable.clothes));
//        items.add(new Item("Kids item", R.drawable.kids));
//        items.add(new Item("Men item", R.drawable.men));
//        items.add(new Item("Women", R.drawable.women));
//        items.add(new Item("School", R.drawable.school));
//        items.add(new Item("Makeup", R.drawable.makup));
//        // Add more items as needed
//        return items;
//    }
//
//    private void displayVerticalItems(Item item) {
//        verticalItems.clear();
//        verticalItems.add(item);
//        // Add additional data to verticalItems based on the selected item
//        // For example:
//        verticalItems.add(new Item("Orange", R.drawable.orange));
//        verticalItems.add(new Item("Apple", R.drawable.apple));
//        verticalItems.add(new Item("Banana", R.drawable.banana));
//        verticalItems.add(new Item("Apple", R.drawable.apple));
//        verticalItems.add(new Item("Guava", R.drawable.guava));
//        verticalItems.add(new Item("Grapes", R.drawable.graps));
//        verticalItems.add(new Item("Watermelon", R.drawable.watermelon));
//        verticalItems.add(new Item("Apple", R.drawable.apples));
//        verticalItems.add(new Item("Graps", R.drawable.fraps));
//        verticalItems.add(new Item("Fruit", R.drawable.fruits));
//        verticalItems.add(new Item("Apple", R.drawable.appless));
//        verticalItems.add(new Item("Banana", R.drawable.banana));
//        verticalItems.add(new Item("Apple", R.drawable.apple));
//        verticalItems.add(new Item("Guava", R.drawable.guava));
//        verticalItems.add(new Item("Grapes", R.drawable.graps));
//        verticalItems.add(new Item("Watermelon", R.drawable.watermelon));
//        verticalItems.add(new Item("Apple", R.drawable.apples));
//        verticalItems.add(new Item("Graps", R.drawable.fraps));
//        verticalItems.add(new Item("Fruit", R.drawable.fruits));
//        verticalItems.add(new Item("Apple", R.drawable.appless));
//        // Add more items as needed
//        verticalAdapter.notifyDataSetChanged();
//
//
//
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
//    private void openMap() {
//        Uri gmmIntentUri = Uri.parse("geo:0,0?q=X747+365+shiv+park,+Tatiri,+Uttar+Pradesh+250601");
//        Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        startActivity(intent);
//
//    }
//
//
//}
