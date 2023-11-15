package com.example.secondtrainep;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
// retrofit

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import androidx.appcompat.app.AppCompatActivity;



public class IntroActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword;
    private Button buttonRegister;
    public TextView signUpText ;
    private SharedPreferences sharedPreferences;
    public ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        apiService = RetrofitClient.getApiService();

        editTextName = findViewById(R.id.etFullName);
        editTextEmail = findViewById(R.id.etEmail);
        editTextPassword = findViewById(R.id.etPassword);
        signUpText = findViewById(R.id.signup_txt);
        buttonRegister = findViewById(R.id.btnSignUp);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUp = new Intent(IntroActivity.this, LogActivity.class);
                startActivity(signUp);
             }

        });

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = editTextName.getText().toString();
                String password = editTextPassword.getText().toString();
                String email = editTextEmail.getText().toString();
                User user = new User(username, password, email);

                Call<ApiResponse> call = apiService.signUp(user);
                call.enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        if (response.isSuccessful()) {

                            Log.e(TAG,"Api Response is Succesfull");
                            // Handle successful sign-up
                        } else {
                            // Log the error details
                            Log.e(TAG, "onResponse Error: " + response.code() + " " + response.message());
                            // Handle sign-up failure
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        // Log the failure reason
                        Log.e(TAG, "onFailure: Sign-up request failed", t);
                        // Handle failure
                    }
                });

                boolean valid = true;

                String name = editTextName.getText().toString().trim();
//                String email = editTextEmail.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();


                saveUserData(name, email);

//                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
//                    Toast.makeText(IntroActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                } else if (!isValidEmail(email)) {
//                    Toast.makeText(IntroActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
//                } else if (!isValidPassword(password)) {
//                    Toast.makeText(IntroActivity.this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
//                } else {

                if (name.length() == 30) {
                    editTextName.setError("Name field is required");

                } else if (TextUtils.isEmpty(name))  {
                    editTextName.setError("please name field ");

                } else  if (email.length() == 0) {
                    editTextEmail.setError("Email field is required");

                } else if ((Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).matches() != true)) {
//            Toast.makeText(MainActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                    editTextEmail.setError("Please enter valid email");

                }     else if (password.length() < 6) {
                    editTextPassword.setError("Number must be minimum 6 characters");


                } else {

                    // Store the registration details in shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", name);
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();

                    SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(IntroActivity.this);
                    sharedPreferencesManager.setLoggedIn(true);

                    Toast.makeText(IntroActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Finish the current activity
                }
            }
        });
    }

    private void saveUserData(String password, String email) {

        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", password);
        editor.putString("email", email);
        editor.apply();
    }


    private boolean email(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    private boolean password(String password) {
        return password.length() >= 6;
    }

    private boolean name(String name) {
        return name.length() ==0 ;
    }
}
