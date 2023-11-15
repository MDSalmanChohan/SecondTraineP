//package com.example.secondtrainep;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toolbar;
//
//
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.MenuItem;
//import android.widget.FrameLayout;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.navigation.NavigationView;
//
//import java.util.ArrayList;
//
//public class MainDrawerActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{
//
//
//
//    private RecyclerView recyclerView;
//    private ArrayList<Integer> imageList;
//    private ImageAdapter adapter;
//    private Button btnMap;
//    private Toolbar toolbar;
//
//    private RecyclerView horizontalRecyclerView;
//    private RecyclerView verticalRecyclerView;
//    private HorizontalAdapter horizontalAdapter;
//    private VerticalAdapter verticalAdapter;
//    private ArrayList<Item> horizontalItems;
//    private ArrayList<Item> verticalItems;
//
//    private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle actionBarDrawerToggle;
//    private NavigationView navigationView;
//    private BottomNavigationView bottomNavigationView;
//    public FrameLayout contentFrame;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        contentFrame = findViewById(R.id.content_frame);
//
//        setSupportActionBar(findViewById(R.id.toolbar));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//
//        actionBarDrawerToggle = new ActionBarDrawerToggle(
//                this,
//                drawerLayout,
//                R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close
//        );
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//
//        navigationView.setNavigationItemSelectedListener(this);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
//            // Handle bottom navigation item clicks here
//            switch (item.getItemId()) {
//                case R.id.bottom_nav_home:
//                    loadFragment(new HomeFragment());
//                    return true;
//                case R.id.bottom_nav_search:
//                    loadFragment(new SearchFragment());
//                    return true;
//                case R.id.bottom_nav_notifications:
//                    loadFragment(new NotificationsFragment());
//                    return true;
//                case R.id.bottom_nav_profile:
//                    loadFragment(new ProfileFragment());
//                    return true;
//            }
//            return false;
//        });
//
//        // Set the initial fragment to be loaded
//        loadFragment(new HomeFragment());
//
//
//
//
//
//
//
//
//
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
////    @Override
////    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
////        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
////            return true;
////        }
////        return super.onOptionsItemSelected(item);
////    }
//
//
//    private void loadFragment(Fragment fragment) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.content_frame, fragment)
//                .commit();
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        // Handle navigation drawer item clicks here
//        switch (item.getItemId()) {
//            case R.id.nav_home:
//                loadFragment(new HomeFragment());
//                break;
//            case R.id.nav_profile:
//                loadFragment(new ProfileFragment());
//                break;
//            case R.id.nav_settings:
//                loadFragment(new SettingsFragment());
//                break;
//        }
//
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
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
//
//    }
//
//    private void setupImageRecyclerView() {
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
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
//    private ArrayList<Item> createHorizontalItems() {
//
//
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
//        items.add(new Item("Makeup",R.drawable.makup));
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
//        verticalItems.add(new Item("Guavava", R.drawable.guava));
//        verticalItems.add(new Item("Graps", R.drawable.graps));
//        verticalItems.add(new Item("Banana", R.drawable.banana));
//        verticalItems.add(new Item("Watermelon",R.drawable.watermelon));
//        verticalItems.add(new Item("Orange", R.drawable.orange));
//        verticalItems.add(new Item("Apple", R.drawable.appless));
//        verticalItems.add(new Item("Banana", R.drawable.banana));
//        verticalItems.add(new Item("Orange", R.drawable.orange));
//        verticalItems.add(new Item("Apple", R.drawable.apples));
//        verticalItems.add(new Item("Banana", R.drawable.banana));
//        verticalItems.add(new Item("Guavava", R.drawable.guava));
//        verticalItems.add(new Item("Graps", R.drawable.graps));
//        verticalItems.add(new Item("Banana", R.drawable.banana));
//        verticalItems.add(new Item("Watermelon",R.drawable.watermelon));
//        verticalItems.add(new Item("Orange", R.drawable.orange));
//        verticalItems.add(new Item("Apple", R.drawable.appless));
//        verticalItems.add(new Item("Banana", R.drawable.banana));
//        verticalItems.add(new Item("Orange", R.drawable.orange));
//        verticalItems.add(new Item("Apple", R.drawable.apples));
//        verticalItems.add(new Item("Banana", R.drawable.banana));
//        verticalItems.add(new Item("Orange" , R.drawable.orange));
//        verticalItems.add(new Item("Banana",R.drawable.banana));
//
//
//
//
//        // Add more items as needed
//        verticalAdapter.notifyDataSetChanged();
//    }
//
//    private void incrementQuantity(int position) {
//        Item item = verticalItems.get(position);
//        item.incrementQuantity();
//        verticalAdapter.notifyItemChanged(position);
//
//    }
//
//    private void decrementQuantity(int position) {
//        Item item = verticalItems.get(position);
//        item.decrementQuantity();
//        verticalAdapter.notifyItemChanged(position);
//    }
//
//    private void openMap() {
//        Uri gmmIntentUri = Uri.parse("geo:0,0?q=X747+365+shiv+park,+Tatiri,+Uttar+Pradesh+250601");
//        Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        startActivity(intent);
//        //}
//    }
//
//
//}
