package com.example.secondtrainep;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SupportFragment extends Fragment {

    private ViewPager2 viewPager;
    private Button prevButton;
    private Button nextButton;
    private int currentPage = 0;
    private int numPages = 3; // Number of steps in the form

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_support, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewPager);
        prevButton = view.findViewById(R.id.prevButton);
        nextButton = view.findViewById(R.id.nextButton);

        FragmentStateAdapter pagerAdapter = new FormPagerAdapter(requireActivity());
        viewPager.setAdapter(pagerAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                updateButtons();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage > 0) {
                    viewPager.setCurrentItem(currentPage - 1);
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage < numPages - 1) {
                    viewPager.setCurrentItem(currentPage + 1);
                } else {
                    submitForm();
                }
            }
        });

        updateButtons();
    }

    private void updateButtons() {
        prevButton.setEnabled(currentPage > 0);
        nextButton.setText(currentPage == numPages - 1 ? "Submit" : "Next");
    }

    private void submitForm() {
        // Get data from individual fragments and perform the submission
        StepOneFragment stepOneFragment = (StepOneFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":0");
        StepTwoFragment stepTwoFragment = (StepTwoFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":1");
        StepThreeFragment stepThreeFragment = (StepThreeFragment) getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":2");

        String name = stepOneFragment.getFirstName() + " " + stepOneFragment.getLastName();
        String email = stepTwoFragment.getEmail();
        int age = stepThreeFragment.getAge();

        // Perform form validation here
        if (name.trim().isEmpty() || email.trim().isEmpty() || age == -1) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Do something with the form data (e.g., send it to a server)
        // For this example, we'll just show a success message
        String successMessage = "Form submitted successfully!\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Age: " + age;

        Toast.makeText(requireContext(), successMessage, Toast.LENGTH_LONG).show();
    }

    private static class FormPagerAdapter extends FragmentStateAdapter {

        public FormPagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new StepOneFragment();
                case 1:
                    return new StepTwoFragment();
                case 2:
                    return new StepThreeFragment();
                default:
                    return new StepOneFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
}
