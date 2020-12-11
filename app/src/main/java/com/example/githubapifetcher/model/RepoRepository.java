package com.example.githubapifetcher.model;

import android.app.Application;
import android.app.Dialog;
import android.util.Log;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.githubapifetcher.service.GithubApiEndPoints;
import com.example.githubapifetcher.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoRepository {

    private final MutableLiveData<List<Repo>> mrepos = new MutableLiveData<>();

    private final MutableLiveData<Owner> owner=new MutableLiveData<Owner>();

    private Application mapplication;

    public RepoRepository(Application application){
        mapplication=application;;
    }

    public LiveData<List<Repo>> getRepos(String nickname){

        GithubApiEndPoints apiservice=RetrofitInstance.getService();
        Call<List<Repo>> call=apiservice.getRepos(nickname);

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if(!response.isSuccessful()){
                    Log.i("responsenotsuccessful",response.message());
                }
                mrepos.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.i("onFailure",t.getMessage());

            }
        });
        return mrepos;
    }

    public LiveData<Owner> getOwner(String nickname){
        GithubApiEndPoints apiownerservice=RetrofitInstance.getService();
        Call<Owner> callowner=apiownerservice.getOwner(nickname);

        callowner.enqueue(new Callback<Owner>() {
            @Override
            public void onResponse(Call<Owner> call, Response<Owner> response) {
//                if(!response.isSuccessful()){
//                    Log.i("response code","response code is"+response.code());
//                }
                owner.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Owner> call, Throwable t) {

            }
        });
        return owner;
    }

}
