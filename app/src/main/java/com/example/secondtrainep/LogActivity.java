package com.example.secondtrainep;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//Retrofit
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LogActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword  ;
    public TextView singin_up_txt ;
    private Button buttonLogin;
    private SharedPreferences sharedPreferences;

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        apiService = RetrofitClient.getApiService();


        editTextEmail = findViewById(R.id.usernameEditText);
        editTextPassword = findViewById(R.id.passwordEditText);
        singin_up_txt = findViewById(R.id.singin_up_txt);
        buttonLogin = findViewById(R.id.loginButton);

//        start the creation of this thing code

        singin_up_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUp = new Intent(LogActivity.this, IntroActivity.class);
                startActivity(signUp);



            }
        });


        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                String username = editTextName.getText().toString();
                String password = editTextPassword.getText().toString();
                String email = editTextEmail.getText().toString();
                User user = new User( password, email);

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
//                String email = editTextEmail.getText().toString().trim();
//                String password = editTextPassword.getText().toString().trim();
//
//
//                String usernameEditText = editTextEmail.getText().toString();
//                String passwordEditText = editTextPassword.getText().toString();
//                User user = new User(usernameEditText, passwordEditText);
//
//                // Call your API for user login
////                User user = new User(email, password);
//                Call<ApiResponse> call = apiService.signIn(user);
//                call.enqueue(new Callback<ApiResponse>() {
//                    @Override
//                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                        if (response.isSuccessful()) {
//                            // Handle successful sign-in
//                            Log.e(TAG, "onResponse: Api Response is Successful");
//                            // Save user session or navigate to the main activity
//                        } else {
//                            // Handle sign-in failure
//                            Log.e(TAG, "onResponse: Api Response is Failed");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ApiResponse> call, Throwable t) {
//                        // Handle network failure
//                        Log.e(TAG, "onFailure: Sign-in request failed", t);
//                    }
//                });




//                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
//                    Toast.makeText(LogActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                } else {
//                if (email.length() == 0) {
//                    editTextEmail.setError("Email field is required");
//                }
//                else if ((Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString()).matches() != true)) {
////            Toast.makeText(MainActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
//                    editTextEmail.setError("Please enter valid email");
//
//                }
//                else  if (password.length() <= 6) {
//                    editTextPassword.setError("Password Field is required");
                if (password.length() < 6) {
                    editTextPassword.setError("Password must valid pass");
                    editTextPassword.requestFocus();
                }

                if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Please enter Password");
                    editTextPassword.requestFocus();
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Please enter Valid email");
                    editTextEmail.requestFocus();
                }

                if (TextUtils.isEmpty(email)) {
                    editTextEmail.setError("Please enter User Name");
                    editTextEmail.requestFocus();
                }

                if (!TextUtils.isEmpty(email) &&
                        password.length() >= 6 &&
                        Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                        !TextUtils.isEmpty(password)) {

//                    these are using condition for android


//                }else {
                    String savedEmail = sharedPreferences.getString("username", "");
                    String savedPassword = sharedPreferences.getString("password", "");

                    if (email.equals(savedEmail) && password.equals(savedPassword)) {
                        Toast.makeText(LogActivity.this, "Login successful", Toast.LENGTH_SHORT).show();


                        // Start the next activity

                        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(LogActivity.this);
                        sharedPreferencesManager.setLoggedIn(true);
//                        SharedPreferences loginpref = getSharedPreference("installed", MODE_PRIVATE);


                        Intent intent = new Intent(LogActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Optional: Finish the current activity


                    } else {
                        Toast.makeText(LogActivity.this, "Invalid emails or passwords", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
