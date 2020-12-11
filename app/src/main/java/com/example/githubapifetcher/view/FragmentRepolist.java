package com.example.githubapifetcher.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.githubapifetcher.viewModel.ViewModel;
import com.example.githubapifetcher.R;
import com.example.githubapifetcher.adapter.RepoAdapter;
import com.example.githubapifetcher.model.Owner;
import com.example.githubapifetcher.model.Repo;

import java.util.List;


public class FragmentRepolist extends Fragment {
    private String BASE_URL="https://api.github.com";

    private TextView nameView,nicknameView,textView;
    private ImageView avatarView;
    private RecyclerView recyclerView;
    private ViewModel mviewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repolist,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameView=(TextView)view.findViewById(R.id.user_name);
        nicknameView=(TextView)view.findViewById(R.id.user_nickname);
        avatarView=(ImageView)view.findViewById(R.id.user_image);

        String name=getArguments().getString("nickname");

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mviewModel=new ViewModelProvider
                .AndroidViewModelFactory(getActivity().getApplication())
                .create(ViewModel.class);

        loadOwner(name);

        loadRepos(name);

    }

    private void loadRepos(String name) {
        mviewModel.getRepos(name).observe(getViewLifecycleOwner(), new Observer<List<Repo>>() {
            @Override
            public void onChanged(List<Repo> repos) {
                Toast.makeText(getActivity(),"Repository List updated",Toast.LENGTH_SHORT).show();
                RepoAdapter mRepoAdapter=new RepoAdapter(getActivity());
                mRepoAdapter.setRepos(repos);
                recyclerView.setAdapter(mRepoAdapter);
                mRepoAdapter.notifyDataSetChanged();

            }
        });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        GithubApiEndPoints apisevice=retrofit.create(GithubApiEndPoints.class);
//        Call<List<Repo>> callrepo=apisevice.getRepos(name);
//
//        callrepo.enqueue(new Callback<List<Repo>>() {
//            @Override
//            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
////                if(!response.isSuccessful()){
////                    textView.setText("CallresponseError:"+response.code());
////                    return;
////                }
//
//                List<Repo> repos=new ArrayList<Repo>();
//                repos=response.body();
//
//                RepoAdapter repoAdapter=
//                        new RepoAdapter(getContext(),repos);
//
//                recyclerView.setAdapter(repoAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Repo>> call, Throwable t) {
//
//            }
//        });
    }

    private void loadOwner(String name) {
      mviewModel.getOwner(name).observe(getActivity(), new Observer<Owner>() {
          @Override
          public void onChanged(Owner owner) {
              setOwnerData(owner);
          }
      });
    }

    private void setOwnerData(Owner owner){

        nameView.setText(owner.getName());
        nicknameView.setText(owner.getLogin());
        Glide.with(getContext()).load(owner.getAvatarUrl()).centerCrop().into(avatarView);

    }
}
