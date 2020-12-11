package com.example.githubapifetcher.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static String BASE_URL="https://api.github.com";
    private static Retrofit retrofit=null;

    public static GithubApiEndPoints getService(){


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            GithubApiEndPoints apisevice=retrofit.create(GithubApiEndPoints.class);

        return retrofit.create(GithubApiEndPoints.class);

    }
}
