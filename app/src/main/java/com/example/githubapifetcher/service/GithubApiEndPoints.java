package com.example.githubapifetcher.service;

import com.example.githubapifetcher.model.Owner;
import com.example.githubapifetcher.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApiEndPoints {

    @GET("users/{nickname}/repos")
    Call<List<Repo>> getRepos(@Path("nickname")String nickname);

    @GET("users/{nickname}/repos")
    Call<List<Repo>> getPagedREpos(
            @Path("nickname")String nickname,
            @Query("page")int pageno,
            @Query("per_page")int per_page
    );

    @GET("users/{nickname}")
    Call<Owner> getOwner(@Path("nickname")String nickname);
}
