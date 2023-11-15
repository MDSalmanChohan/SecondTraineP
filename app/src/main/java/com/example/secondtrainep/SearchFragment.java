package com.example.secondtrainep;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SearchFragment extends Fragment {

    private String[] cities = {"Paris", "New York", "Tokyo", "Barcelona", "Sydney"};
    private String[] descriptions = {
            "The City of Love", "The Big Apple", "The Capital of Japan",
            "Gaudi's Masterpiece", "The Land Down Under"
    };
    private int[] cityImages = {
            R.drawable.paris, R.drawable.newyork, R.drawable.tokyo,
            R.drawable.bersilona, R.drawable.sydney
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView = view.findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, cities);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityName = cities[position];
                Toast.makeText(requireContext(), "You selected: " + cityName, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
