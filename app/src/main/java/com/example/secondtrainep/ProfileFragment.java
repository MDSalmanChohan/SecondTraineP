package com.example.secondtrainep;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 2;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String PROFILE_PHOTO_URI_KEY = "profile_photo_uri";



    private TextView nameTextView, emailTextView;

    private ImageView imageViewProfile;
    public Button btnEmail;
    private SharedPreferences preferences;
    public Uri capturedPhotoUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize views
        nameTextView = view.findViewById(R.id.profileName);
        btnEmail = view.findViewById(R.id.btnEmail);
        emailTextView = view.findViewById(R.id.profileEmail);
        imageViewProfile = view.findViewById(R.id.imageViewProfile);
        Button buttonChangePhoto = view.findViewById(R.id.buttonChangePhoto);

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });


        preferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Load the profile photo URI from SharedPreferences and set it to the ImageView
        String profilePhotoUriString = preferences.getString(PROFILE_PHOTO_URI_KEY, null);
        if (profilePhotoUriString != null) {
            capturedPhotoUri = Uri.parse(profilePhotoUriString);
            imageViewProfile.setImageURI(capturedPhotoUri);
        }

        // Request camera permission and handle the button click to capture photo
        buttonChangePhoto.setOnClickListener(v -> requestCameraPermission());

        // Load user data from SharedPreferences and display it
        loadUserData();

        return view;
    }

    private void sendEmail() {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:imrankhan14894@gmail.com"));
        intent.putExtra(Intent.EXTRA_EMAIL, "Application for exited person ");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Hello");
        intent.putExtra(Intent.EXTRA_TEXT, "This is the body of the email || Email write here in this block. ");
        startActivity(intent);
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            startCameraIntent();
        }
    }

    private void startCameraIntent() {
        Intent capturePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (capturePhotoIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivityForResult(capturePhotoIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCameraIntent();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            if (data != null && data.getExtras() != null) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap != null) {
                    // Save the captured photo to the gallery
                    Uri photoUri = savePhotoToGallery(bitmap);

                    // Save the captured photo URI to SharedPreferences
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(PROFILE_PHOTO_URI_KEY, photoUri.toString());
                    editor.apply();

                    // Set the profile image in the ImageView
                    imageViewProfile.setImageURI(photoUri);
                }
            }
        }
    }

    private Uri savePhotoToGallery(Bitmap bitmap) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + ".jpg";

        // Save the image to the gallery
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(storageDir, imageFileName);
        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            MediaStore.Images.Media.insertImage(requireActivity().getContentResolver(), imageFile.getAbsolutePath(), imageFileName, null);
            return Uri.fromFile(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadUserData() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "Guest");
        String email = sharedPreferences.getString("email", "guest@example.com");


        nameTextView.setText(name);
        emailTextView.setText(email);
        // Update your TextViews here with the loaded data
    }
}
