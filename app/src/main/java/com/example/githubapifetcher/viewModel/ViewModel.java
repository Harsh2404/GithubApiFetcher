package com.example.githubapifetcher.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.githubapifetcher.model.Owner;
import com.example.githubapifetcher.model.Repo;
import com.example.githubapifetcher.model.RepoRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private RepoRepository mrepoRepository;

    public ViewModel(@NonNull Application application) {
        super(application);
        mrepoRepository=new RepoRepository(application);
    }
    public LiveData<List<Repo>> getRepos(String nickname){
        return mrepoRepository.getRepos(nickname);
    }

    public  LiveData<Owner> getOwner(String nickname){
        return mrepoRepository.getOwner(nickname);
    }
}
