package com.jikansoftware.retrofitjsonpractica4.retrofit;

import com.jikansoftware.retrofitjsonpractica4.reposervice.ApiService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    /********
     * URLS
     *******/
    private static final String ROOT_URL = "http://pratikbutani.x10.mx";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
