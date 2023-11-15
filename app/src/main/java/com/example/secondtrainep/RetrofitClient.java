package com.example.secondtrainep;


    import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class RetrofitClient {

//    for login url --    https://jsonplaceholder.typicode.com/ --  https://jsonplaceholder.typicode.com/users
//        private static final String BASE_URL = "https://reqres.in/api/login/"; // Replace with your API base URL



        private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts/"; // Replace with your API base URL
        private static ApiService apiService;

        public static ApiService getApiService() {
            if (apiService == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                apiService = retrofit.create(ApiService.class);
            }
            return apiService;
        }


}


