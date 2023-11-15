package com.example.secondtrainep;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class StepThreeFragment extends Fragment {

    private EditText ageEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_step_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ageEditText = view.findViewById(R.id.ageEditText);
    }

    public int getAge() {
        try {
            String ageStr = ageEditText.getText().toString().trim();
            return Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
